package server;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import logic.DataBase;
import parser.NewDataBase;

import static logic.json.Json.json;

public class Node extends AbstractVerticle {
    
    Vertx vertx;

    Node(Vertx vertx) {
        this.vertx = vertx;
    }

    // Sends the HTTP response object with the given status code and JSON object
    void sendReponse(RoutingContext ctx, int statusCode, JsonObject json) {
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "text/json");
        response.setStatusCode(statusCode);
        response.end(json.encodePrettily());
    }

    void ping(RoutingContext ctx, JsonObject request) {
        JsonObject response = new JsonObject();
        response.put("message", "This node is up.");
        sendReponse(ctx, 200, response);
    }

    void createTable(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String table_headers = request.getString("table_headers");
        System.out.println("/createtable with table_name=" + table_name + " and table_headers=" + table_headers);
        // Actual behaviour
        JsonObject response = new JsonObject();
        response.put("message", "Successfully created a new table.");
        //DataBase.getInstance().newTable(table_name, );
        // NEW JSON CREATE TABLE MAP STRING STRING NOM DE COLONNE / TYPE
    }

    void createIndex(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String columns = request.getString("columns");
        System.out.println("/createindex with table_name=" + table_name + " and columns=" + columns);
        // Actual behaviour
        try {
            DataBase.getInstance().getTables().get(table_name).createIndex(new String[]{columns});
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        JsonObject response = new JsonObject();
        response.put("message", "Successfully updated indices.");

        //JSON POUR CREATE INDEX AVEC TABLEAU DES NOMS DES COLONNES

        DataBase.getInstance().getTables().get("Table_Name").getIndex().get(columns).traverse();


    }

    void uploadCSV(RoutingContext ctx, JsonObject request) {
        String table_name = request.getString("table_name");
        String file_path = request.getString("file_name");
        System.out.println("/uploadcsv with table_name=" + table_name + " and file_path=" + file_path + ".");

        NewDataBase ndb = new NewDataBase();
        try {
            ndb.inIndex(new File(file_path));
        } catch (IOException e) {
            JsonObject response = new JsonObject();
            response.put("error", "Cannot read the given CSV file.");
            sendReponse(ctx, 500, response);
            e.printStackTrace();
            return;
        }

        System.out.println(DataBase.getInstance().getTables().get("Table_Name").toString());

    }

    void get(RoutingContext ctx, JsonObject request) throws NoSuchAlgorithmException{
        String result = "";
        String table = request.getString("table");
        String method = request.getString("method");
        String args = request.getString("args");
        System.out.println("/get with table_name=" + table + " and query=" + method + " and args=" + args);
        if (table == null || method == null) {
            JsonObject response = new JsonObject();
            response.put("error", "Needed params in body: table_name, method, args.");
            sendReponse(ctx, 422, response);
        }
        // Actual handling
        JsonObject response = new JsonObject();

        String data = request.toString();
        result = json(data);

        response.put("data", result);
        sendReponse(ctx, 200, response);
    }

    // Get the JSON body from a request
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
