package server;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.getOrCreateContext().put("node", "master");
    vertx.deployVerticle(new Server(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext){
    testContext.completeNow();
  }

  @Test
  void ping_node_1(Vertx vertx, VertxTestContext testContext){
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    params.put("node", "1");
    client.post(8888, "localhost", "/pingnode")
            .sendJson(params)
            .onSuccess(response -> {
              testContext.completeNow();
            })
            .onFailure(err -> {
              testContext.failNow("Cannot ping the master node");
            });
  }

  @Test
  void ping_node_master(Vertx vertx, VertxTestContext testContext){
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    params.put("node", "master");
    client.post(8888, "localhost", "/pingnode")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getBoolean("up")) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("master node should be up");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot ping the master node");
            });
  }

  @Test
  void wrong_route(Vertx vertx, VertxTestContext testContext){
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    params.put("node", "master");
    client.post(8888, "localhost", "/wrongroute")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getString("error") != null) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("should throw error");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot send request");
            });
  }

  @Test
  void get(Vertx vertx, VertxTestContext testContext) {
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    client.post(8888, "localhost", "/get")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getString("error") != null) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("should throw error because missing required params");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot send request");
            });
  }

  @Test
  void createindex(Vertx vertx, VertxTestContext testContext){
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    client.post(8888, "localhost", "/createindex")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getString("error") != null) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("should throw error because missing required params");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot send request");
            });
  }

  @Test
  void createtable(Vertx vertx, VertxTestContext testContext) {
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    client.post(8888, "localhost", "/createtable")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getString("error") != null) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("should throw error");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot send request because missing required params");
            });
  }

  @Test
  void uploadcsv(Vertx vertx, VertxTestContext testContext){
    WebClient client = WebClient.create(vertx);
    JsonObject params = new JsonObject();
    client.post(8888, "localhost", "/uploadcsv")
            .sendJson(params)
            .onSuccess(response -> {
              JsonObject json = response.bodyAsJsonObject();
              if (json.getString("error") != null) {
                testContext.completeNow();
              }
              else {
                testContext.failNow("should throw error because missing required params");
              }
            })
            .onFailure(err -> {
              testContext.failNow("cannot send request");
            });
  }
}