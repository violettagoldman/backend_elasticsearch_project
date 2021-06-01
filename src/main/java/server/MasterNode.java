package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Base64;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class MasterNode extends AbstractVerticle {

    Vertx vertx;

    MasterNode(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * Sends the HTTP response object with the given status code and JSON object
     * @param  ctx  vertx context
     * @param  statusCode the satus code we want to send
     * @param  json response
     */
    void sendReponse(RoutingContext ctx, int statusCode, JsonObject json) {
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "text/json");
        response.setStatusCode(statusCode);
        response.end(json.encodePrettily());
    }

    /**
     * Sends a request to a simple node, not master
     * @param  route an api route to call
     * @param  node node index (1, 2, 3)
     * @param  params args, the request
     * @return the request future
     */
    Future<HttpResponse<Buffer>> nodeRequest(String route, String node, JsonObject params) {
        WebClient client = WebClient.create(vertx);
        return client.post(Server.ports.get(node), "localhost", "/" + route)
                .sendJsonObject(params);
    }

    /**
     * Tell the node to create a new table
     * Needed params:
     * - table_name: string, name of the table
     * - table_headers: strings separated with ';', name of the columns
     * @param  ctx  vertx context
     * @param  request args, the request
     */
    void createTable(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String table_headers = request.getString("table_headers");
        if (table_name == null || table_headers == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name, table_headers.");
            sendReponse(ctx, 422, response);
            return;
        }

        for (int server = 1; server < 4; ++server) {
            nodeRequest("/createtable", String.valueOf(server), request);
        }

        JsonObject response = new JsonObject();
        response.put("message", "Successfully created a new table.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Create a new index for a table
     * Needed params:
     * - table_name: string, name of the table to update
     * - table_headers: strings separated with ';', name of the columns
     * @param  ctx  vertx context
     * @param  request args, the request
     */
    void createIndex(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String columns = request.getString("columns");
        if (table_name == null || columns == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name, columns.");
            sendReponse(ctx, 422, response);
            return;
        }

        for (int server = 1; server < 4; ++server) {
            nodeRequest("/createindex", String.valueOf(server), request);
        }

        JsonObject response = new JsonObject();
        response.put("message", "Successfully updated indices.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Upload CSV data in the given table. SHOULD BE CALLED WITH FORM-DATA, not JSON.
     * Params:
     * - table_name: string, name of the table to update
     * - csv: string, CSV content to add in the tablethe columns
     * @param  ctx  vertx context
     */
    void uploadCSV(RoutingContext ctx) throws IOException {
        String table_name = ctx.request().getFormAttribute("table_name");
        // String file = "";
        if (table_name == null || ctx.fileUploads().size() == 0) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name and a file.");
            sendReponse(ctx, 422, response);
            return;
        }

        String path = "";
        for (FileUpload f : ctx.fileUploads()) {
            path = f.uploadedFileName();
        }
        // String base64 = new String(Base64.getEncoder().encode(Files.readString(Path.of(path)).getBytes()));
        // System.out.println(base64);

        JsonObject params = new JsonObject();
        params.put("table_name", table_name);
        params.put("file_name", path);
        for (int server = 1; server < 4; ++server) {
            nodeRequest("/uploadcsv", String.valueOf(server), params);
        }

        JsonObject response = new JsonObject();
        response.put("message", "Successfully uploaded data.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Get data from the given table
     * Params:
     * - table_name: string, name of the table to update
     * - query: string, query to get the desired lines
     * @param  ctx  vertx context
     */
    void get(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String query = request.getString("query");
        if (table_name == null || query == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name, query.");
            sendReponse(ctx, 422, response);
            return;
        }

        // We choose a random server to get the data
        int server = 1 + (int)(Math.random() * 3);
        System.out.println("Forwarding get request to server " + server + ".");
        nodeRequest("/get", String.valueOf(server), request).onComplete(resp -> {
            if (resp.succeeded()) {
                sendReponse(ctx, 200, resp.result().bodyAsJsonObject());
            } else {
                JsonObject response = new JsonObject();
                response.put("error", "Cannot get a response from server " + server + ".");
                sendReponse(ctx, 500, response);
            }
        });
    }

    /**
     * Check if the given node is running
     * Params:
     * - node: string, the number of the node to ping
     * @param  ctx  vertx context
     * @param  request args, the request
     */
    void ping(RoutingContext ctx, JsonObject request) {
        String node = request.getString("node");
        if (node == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Missing param in body: node (1, 2, or 3).");
            sendReponse(ctx, 422, response);
            return;
        }
        nodeRequest("ping", node, new JsonObject()).onSuccess(result -> {
            System.out.println(result.bodyAsJsonObject());
            JsonObject response = new JsonObject();
            response.put("message", "The node is up!");
            response.put("up", true);
            sendReponse(ctx, 200, response);
            return;
        }).onFailure(error -> {
            JsonObject response = new JsonObject();
            response.put("message", "The node is down.");
            response.put("up", false);
            sendReponse(ctx, 200, response);
            return;
        });
    }

    /**
     * Get the JSON body from a request
     * @param  ctx  vertx context
     * @return json request
     */
    private JsonObject parseBody(RoutingContext ctx) {
        try {
            return ctx.getBodyAsJson();
        } catch (Exception e) {
            JsonObject response = new JsonObject();
            response.put("error", "Bad JSON.");
            sendReponse(ctx, 422, response);
            return null;
        }
    }

    /**
     * setup routes
     * @param  router  vetrtx router
     */
    public void setupRoutes(Router router) {
        // Route createtable to create a new table
        router.route("/createtable").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                createTable(ctx, body);
        });

        // Route createindex to create a new index on a table
        router.route("/createindex").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                createIndex(ctx, body);
        });

        // Route uploadcsv that uploads the given CSV content into the given table
        router.route("/uploadcsv").handler(ctx -> {
            try {
                uploadCSV(ctx);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Route get that returns lines matching the given query
        router.route("/get").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                get(ctx, body);
        });

        // Ping all nodes to see if they are running
        router.route("/pingnode").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                ping(ctx, body);
        });

        // Default route
        router.route().handler(ctx -> {
            JsonObject response = new JsonObject();
            response.put("error", "Unknown endpoint. Available endpoints: /createtable, /createindex, /uploadcsv, /get, /pingnode.");
            sendReponse(ctx, 404, response);
        });

        router.errorHandler(500, rc -> {
            System.err.println("Handling failure");
            Throwable failure = rc.failure();
            if (failure != null) {
                failure.printStackTrace();
            }
        });
    }

}