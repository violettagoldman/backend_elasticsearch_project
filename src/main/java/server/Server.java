package server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.FileUpload;

import java.util.Map;

import distribution.Routing;

public class Server extends AbstractVerticle {

  final const int NUMBER_OF_NODES = 3;
  final const int NUMBER_OF_SHARDS = 5;
  String nodes[NUMBER_OF_NODES] = {
    "localhost:8889",
    "localhost:8890",
    "localhost:8891"
  };

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
    for (int i = 0; i < NUMBER_OF_NODES; i++) {
      // Send a "createTable" request to nodes[i]
      // You need to perform a HTTP request to the URL nodes[i]
      // The Node class should be able to receive this request and call its internal createTable nethod
    }

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
    String file = "";
    if (table_name == null || ctx.fileUploads().size() == 0) {
      JsonObject response = new JsonObject();
      response.put("error", "Needed params in body: table_name and a file.");
      sendReponse(ctx, 422, response);
    }
    // Actual handling
    for (FileUpload f : ctx.fileUploads()) {
      file = f.uploadedFileName();
    }
    // Here for evey lines of the CSV you should:
    // Calculate the shard in which the line should be stored with Routing.getShard()
    // Send a HTTP request to all nodes to insert the line in the calculated shard
    System.out.println(file);
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
    System.out.println(Routing.getShard(query, 5));

    List<String> results = new List<String>();
    Map<int, int> routes = Routing.getRoutes(NUMBER_OF_NODES, NUMBER_OF_SHARDS);
    for (int i = 0; i < NUMBER_OF_SHARDS; i++) {
      String node = nodes[routes[i]];
      // Serch for query on node with shard i
      // results.add(result of the query)
    }

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
    router.route().handler(BodyHandler.create().setUploadsDirectory("./upload"));

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
