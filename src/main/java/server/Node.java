package server;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import logic.DataBase;
import org.json.JSONObject;
import parser.CSVParser;

import static logic.json.jsonGet.JsonGet.json;
import static logic.json.jsonIndex.JsonIndex.jsonIndex;
import static logic.json.jsonTable.JsonTable.jsonTable;


public class Node extends AbstractVerticle {

    Vertx vertx;

    Node(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * Sends the HTTP response object with the given status code and JSON object
     *
     * @param ctx        vertx context
     * @param statusCode the satus code we want to send
     * @param json       response
     */
    void sendReponse(RoutingContext ctx, int statusCode, JsonObject json) {
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "text/json");
        response.setStatusCode(statusCode);
        response.end(json.encodePrettily());
    }

    /**
     * Checks if the current node is running
     *
     * @param ctx     vertx context
     * @param request json request
     */
    void ping(RoutingContext ctx, JsonObject request) {
        JsonObject response = new JsonObject();
        response.put("message", "This node is up.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Creates the table
     *
     * @param ctx     vertx context
     * @param request json request
     */
    void createTable(RoutingContext ctx, JsonObject request) {
        Map<String, String> result = null;
        String table_name = request.getString("table_name");
        String columns = request.getString("columns");
        System.out.println("/createtable with table_name=" + table_name + " and table_headers=" + columns);

        // Actual behaviour
        String data = request.toString();
        ArrayList columnsNames;
        ArrayList columnsTypes;
        try {
            columnsNames = (ArrayList) jsonTable(data, true);
            columnsTypes = (ArrayList) jsonTable(data, false);
            DataBase.getInstance().newTable(table_name, columnsNames, columnsTypes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        JsonObject response = new JsonObject();
        response.put("message", "Successfully created a new table.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Creates the index
     *
     * @param ctx     vertx context
     * @param request json request
     */
    void createIndex(RoutingContext ctx, JsonObject request) {
        String[] result = null;
        String table_name = request.getString("table_name");
        String columns = request.getString("columns");
        System.out.println("/createindex with table_name=" + table_name + " and columns=" + columns);

        // Actual behaviour
        String data = request.toString();
        try {
            result = jsonIndex(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            DataBase.getInstance().createIndex(table_name, result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        DataBase.getInstance().getTables().get("Table_Name").getIndex().get(columns).traverse();
        System.out.println(DataBase.getInstance().getTables().get("Table_Name").toString());
        JsonObject response = new JsonObject();
        response.put("message", "Successfully updated indices.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Uploads CSV
     *
     * @param ctx     vertx context
     * @param request json request
     */
    void uploadCSV(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String file_path = request.getString("file_name");
        System.out.println("/uploadcsv with table_name=" + table_name + " and file_path=" + file_path + ".");
        CSVParser csvp = new CSVParser();
        try {
            csvp.readFileLocal(new File(file_path), DataBase.getInstance().getTables().get(table_name));
        } catch (IOException e) {
            JsonObject response = new JsonObject();
            response.put("error", "Cannot read the given CSV file.");
            sendReponse(ctx, 500, response);
            e.printStackTrace();
        }
        DataBase.getInstance().getTables().get("Table_Name").getIndex().get("Age").traverse();
        System.out.println("test");

        DataBase.getInstance().getTables().get("Table_Name").getIndex().get("Age").traverse();
        JsonObject response = new JsonObject();
        response.put("message", "Successfully uploaded data.");
        sendReponse(ctx, 200, response);
    }

    /**
     * Gets the given query
     *
     * @param ctx     vertx context
     * @param request json request
     */
    void get(RoutingContext ctx, JsonObject request) throws NoSuchAlgorithmException {
        String table = request.getString("table");
        String method = request.getString("method");
        System.out.println("/get with table_name=" + table + " and query=" + method);
        if (table == null || method == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name, method.");
            sendReponse(ctx, 422, response);
        }
        // Actual handling
        JsonObject response = json(request);
        sendReponse(ctx, 200, response);
    }

    /**
     * Gets the JSON body from a request
     *
     * @param ctx vertx context
     * @return json body
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
     *
     * @param router vetrtx router
     */
    public void setupRoutes(Router router) {

        // Test route to see if the node is connected
        router.route("/ping").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                ping(ctx, body);
        });

        router.route("/createtable").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                createTable(ctx, body);
        });

        router.route("/createindex").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                createIndex(ctx, body);
        });

        router.route("/uploadcsv").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null)
                uploadCSV(ctx, body);
        });

        router.route("/get").handler(ctx -> {
            JsonObject body = parseBody(ctx);
            if (body != null) {
                try {
                    get(ctx, body);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        // Default route
        router.route().handler(ctx -> {
            JsonObject response = new JsonObject();
            response.put("error", "Unknown endpoint. Available endpoints: /createtable, /createindex, /uploadcsv, /get.");
            sendReponse(ctx, 404, response);
        });
    }
}