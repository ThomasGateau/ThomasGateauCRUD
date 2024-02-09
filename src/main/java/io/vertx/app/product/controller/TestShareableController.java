package io.vertx.app.product.controller;

import io.vertx.app.product.service.TestShareableService;
import io.vertx.app.product.service.impl.DefaultTestShareableService;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.RoutingContext;

public class TestShareableController {

    private final TestShareableService testShareableService;

    public TestShareableController(LocalMap<String, JsonArray> testsCollectionsSharedData) {
        this.testShareableService = new DefaultTestShareableService(testsCollectionsSharedData);
    }

    public void tests(RoutingContext context) {
        context.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(testShareableService.listTests()));
    }

    public void test(RoutingContext context) {
        Number testId = Integer.parseInt(context.request().getParam("id"));
        context.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(testShareableService.testById(testId)));
    }

    public void createTest(RoutingContext context) {
        JsonObject newTest = context.body().asJsonObject();
        testShareableService.createTest(newTest);
        context.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(201) // Created
                .end(Json.encodePrettily(newTest));
    }

    public void updateTest(RoutingContext context) {
        Number testId = Integer.parseInt(context.request().getParam("id"));
        JsonObject updatedTest = context.body().asJsonObject();
        testShareableService.updateTest(testId, updatedTest);
        context.response().setStatusCode(204).end(); // No content
    }

    public void deleteTest(RoutingContext context) {
        Number testId = Integer.parseInt(context.request().getParam("id"));
        testShareableService.deleteTest(testId);
        context.response().setStatusCode(204).end(); // No content
    }

}
