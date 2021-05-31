package server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.Map;

public class Server extends AbstractVerticle {

  static public Map<String, Integer> ports = new HashMap<String, Integer>();

  // "master", 8888,
  //   "1", 8889,
  //   "2", 8890,
  //   "3", 8891

  @Override
  public void start() throws Exception {
    ports.put("master", 8888);
    ports.put("1", 8889);
    ports.put("2", 8890);
    ports.put("3", 8891);
    String node_name = System.getenv("node");
    if (node_name == null) {
      System.out.println("No \"node\" env variable. Should be:");
      System.out.println("- master");
      System.out.println("- 1");
      System.out.println("- 2");
      System.out.println("- 3");
      node_name = "master";
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
