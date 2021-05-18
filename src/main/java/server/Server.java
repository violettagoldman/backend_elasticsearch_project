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
import logic.DataBase;
import parser.CSVParser;
import parser.NewDataBase;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Server extends AbstractVerticle {

  static public Map<String, Integer> ports = Map.of(
    "master", 8888,
    "1", 8889,
    "2", 8890,
    "3", 8891
  );

  @Override
  public void start() throws Exception {

    String node_name = System.getenv("node");
    if (node_name == null) {
      System.out.println("No \"node\" env variable. Should be:");
      System.out.println("- master");
      System.out.println("- 1");
      System.out.println("- 2");
      System.out.println("- 3");
      getVertx().close();
      System.exit(1);
    }

    if (!node_name.equals("master") && !node_name.equals("1") && !node_name.equals("2") && !node_name.equals("3")) {
      System.out.println("No node named \"" + node_name + "\". Should be:");
      System.out.println("- master");
      System.out.println("- 1");
      System.out.println("- 2");
      System.out.println("- 3");
      getVertx().close();
      System.exit(1);
    }

    System.out.println("Starting node " + node_name);

    // We create a HTTP server
    HttpServer server = vertx.createHttpServer();
    // We create a router that will be mapping a route "i.e. localhost:8888/upload/" to a functions
    Router router = Router.router(vertx);

    // We add a body handler to get the data sent to the server
    router.route().handler(BodyHandler.create().setUploadsDirectory("./upload"));

    // We choose wether to create a master node or a simple node
    AbstractVerticle verticle;
    if (node_name.equals("master")) {
      verticle = new MasterNode(getVertx());
      ((MasterNode)verticle).setupRoutes(router);
    } else {
      verticle = new Node(getVertx());
      ((Node)verticle).setupRoutes(router);
    }

    server.requestHandler(router).listen(ports.get(node_name));
  }
}
