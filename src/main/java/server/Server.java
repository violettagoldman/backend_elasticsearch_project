package server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.core.http.HttpMethod;

public class Server extends AbstractVerticle {

  // Sends the HTTP response object with the given status code and JSON object
  void sendReponse(RoutingContext ctx, int statusCode, JsonObject json) {
    HttpServerResponse response = ctx.response();
    response.putHeader("content-type", "text/json");
    response.setStatusCode(statusCode);
    response.end(json.encodePrettily());
  }

  /* Create a new table
  Params:
  - table_name: string, name of the table
  - table_headers: strings separated with ';', name of the columns
  */
  void createTable(RoutingContext ctx) {
    String table_name = ctx.request().getFormAttribute("table_name");
    String table_headers = ctx.request().getFormAttribute("table_headers");
    if (table_name == null || table_headers == null) {
      JsonObject response = new JsonObject();
      response.put("error", "Needed params in body: table_name, table_headers.");
      sendReponse(ctx, 422, response);
    }
    // Actual handling
    JsonObject response = new JsonObject();
    response.put("message", "Successfully created a new table.");
    sendReponse(ctx, 200, response);
  }

  /* Create a new index for a table
  Params:
  - table_name: string, name of the table to update
  - columns: strings separated with ';', the column to make index
  */
  void createIndex(RoutingContext ctx) {
    String table_name = ctx.request().getFormAttribute("table_name");
    String columns = ctx.request().getFormAttribute("columns");
    if (table_name == null || columns == null) {
      JsonObject response = new JsonObject();
      response.put("error", "Needed params in body: table_name, columns.");
      sendReponse(ctx, 422, response);
    }
    // Actual handling
    JsonObject response = new JsonObject();
    response.put("message", "Successfully updated indices.");
    sendReponse(ctx, 200, response);
  }

  /* Upload CSV data in the given table
  Params:
  - table_name: string, name of the table to update
  - csv: string, CSV content to add in the table
  */
  void uploadCSV(RoutingContext ctx) {
    String table_name = ctx.request().getFormAttribute("table_name");
    String csv = ctx.request().getFormAttribute("csv");
    if (table_name == null || csv == null) {
      JsonObject response = new JsonObject();
      response.put("error", "Needed params in body: table_name, csv.");
      sendReponse(ctx, 422, response);
    }
    // Actual handling
    JsonObject response = new JsonObject();
    response.put("message", "Successfully uploaded data.");
    sendReponse(ctx, 200, response);
  }

  /* Get data from the given table
  Params:
  - table_name: string, name of the table to update
  - query: string, query to get the desired lines
  */
  void get(RoutingContext ctx) {
    String table_name = ctx.request().getFormAttribute("table_name");
    String query = ctx.request().getFormAttribute("query");
    if (table_name == null || query == null) {
      JsonObject response = new JsonObject();
      response.put("error", "Needed params in body: table_name, query.");
      sendReponse(ctx, 422, response);
    }
    // Actual handling
    JsonObject response = new JsonObject();
    response.put("message", "Successfully uploaded data.");
    response.put("data", "[]");
    sendReponse(ctx, 200, response);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // We create a HTTP server
    HttpServer server = vertx.createHttpServer();
    // We create a router that will be mapping a route "i.e. localhost:8888/upload/" to a functions
    Router router = Router.router(vertx);

    // We add a body handler to get the data sent to the server
    router.route().handler(BodyHandler.create());

    // Route createtable to create a new table
    router.route("/createtable").handler(ctx -> {
      createTable(ctx);
    });

    // Route createindex to create a new index on a table
    router.route("/createindex").handler(ctx -> {
      createIndex(ctx);
    });
  
    // Route uploadcsv that uploads the given CSV content into the given table
    router.route("/uploadcsv").handler(ctx -> {
      uploadCSV(ctx);
    });
  
    // Route get that returns lines matching the given query
    router.route("/get").handler(ctx -> {
      get(ctx);
    });

    // Default route
    router.route().handler(ctx -> {
      JsonObject response = new JsonObject();
      response.put("error", "Unknown endpoint. Available endpoints: /createtable, /createindex, /uploadcsv, /get.");
      sendReponse(ctx, 404, response);
    });

    server.requestHandler(router).listen(8888);
  }
}
