package org.restapi.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MsResassuredSteps {
    private Response response;
    private RequestSpecification request;
    private String requestBody;

    @Given("base uri {string}")
    public void baseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
        request = given();
    }

    @Given("set end point {string}")
    public void setEndPoint(String endPoint) {
        request.basePath(endPoint);
    }

    @Then("log all the response")
    public void logResponse() {

        response.then().log().all();
    }

    @When("payload is set from path {string}")
    public void setPayload(String jsonPath) {
        try {
            String payLoad = new String(Files.readAllBytes(Paths.get(jsonPath)));
            request.body(payLoad);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("{string} request is sent")
    public void sendRequest(String method) {
        request.contentType("application/json");
        request.log().all();
        switch (method.toUpperCase()) {
            case "GET":
                response = request.get();
                break;
            case "POST":
                response = request.post();
                break;
            case "PUT":
                response = request.put();
                break;
            case "DELETE":
                response = request.delete();
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }

    @And("set header type {string} as {string}")
    public void setHeader(String key, String value) {
        request.header(key, value);
    }

    @And("set query param {string} as {string}")
    public void setQueryParam(String key, String value) {
        request.queryParams(key, value);
    }

    @Then("the response code should be {int}")
    public void responseCode(int code) {
        assertEquals("Unexpected response code", code, response.getStatusCode());
    }
}

