package server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

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

        // Default route
        router.route().handler(ctx -> {
            JsonObject response = new JsonObject();
            response.put("error", "Unknown endpoint. Available endpoints: /createtable, /createindex, /uploadcsv, /get.");
            sendReponse(ctx, 404, response);
        });
    }

}
