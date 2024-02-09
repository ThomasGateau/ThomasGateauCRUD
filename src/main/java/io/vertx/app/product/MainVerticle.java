package io.vertx.app.product;

import io.vertx.app.product.controller.TestShareableController;
import io.vertx.core.*;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  private static final String TESTS_COLLECTION = "tests";
  private TestShareableController testShareableController;

  @Override
  public void start(Promise<Void> startFuture) {
    // Configure routes to handlers
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    // init data
    initDataShareable();

    testShareableController = new TestShareableController(this.vertx.sharedData().getLocalMap(TESTS_COLLECTION));


    // Create the server
    vertx.createHttpServer().requestHandler(getRouter(router)).listen(8080, result -> {
      if (result.succeeded()) {
        logger.info("Vert.x server listening on port 8080");
        startFuture.complete();
      } else {
        logger.info("Vert.x server failed to start");
        logger.error(result.cause());
        startFuture.fail(result.cause().getMessage());
      }
    });
  }

  private Router getRouter(Router router) {
    // Sample endpoint "test"
    router.get("/api/products").handler(testShareableController::tests);
    router.get("/api/products/:id").handler(testShareableController::test);
    // Create product
    router.post("/api/products").handler(testShareableController::createTest);
//    // Retrieve products
//    router.get("/api/products").handler(testShareableController::getAllProducts);
//    // Retrieve product by Id
//    router.get("/api/products/:id").handler(testShareableController::getProductById);
    // Update a product by Id
    router.put("/api/products/:id").handler(testShareableController::updateTest);
    // Delete product
    router.delete("/api/products/:id").handler(testShareableController::deleteTest);


    // Catch-all for non-existent API routes to return a Bad Request status code
    router.route("/api/*").handler(routingContext -> routingContext.response().setStatusCode(400).end());

    // Serve the static resources
    router.route().handler(StaticHandler.create("app").setIndexPage("static/index.html"));

    return router;
  }

  private void initDataShareable() {
    // Sample usage of Shareable
    LocalMap<String, JsonArray> testsCollectionsSharedData = this.vertx.sharedData().getLocalMap(TESTS_COLLECTION);
    testsCollectionsSharedData.put(TESTS_COLLECTION, new JsonArray()
            .add(new JsonObject().put("id", 1).put("name", "test"))
            .add(new JsonObject().put("id", 2).put("name", "test2"))
    );
  }

}
