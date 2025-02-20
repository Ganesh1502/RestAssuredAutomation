package org.restapi.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MsResassuredSteps {
    private Response response;
    private RequestSpecification request;
private String payLoad;
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
            payLoad = new String(Files.readAllBytes(Paths.get(jsonPath)));
            request.body(payLoad);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("set the tag value {string} from stored property {string}")
    public void setFromProperty(String tag, String key) {
        String path = "data.properties";
        Properties property = new Properties();

        try (FileInputStream input = new FileInputStream(path)) {
            property.load(input);
            String tagValue = property.getProperty(key);

            if (tagValue != null && payLoad != null) {
                // Convert stored payload string to JSON object
                JSONObject jsonPayload = new JSONObject(payLoad);

                // Add/Update the dynamic tag key with value from properties
                jsonPayload.put(tag, tagValue);

                // Set updated payload back to request body
                request.body(jsonPayload.toString());
                payLoad = jsonPayload.toString();  // Update stored payload

                System.out.println("Updated Payload: " + jsonPayload.toString());
            } else {
                System.out.println("Property key not found: " + key);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    @Then("store the tag value {string}")
    public void storeData(String tagKey) throws FileNotFoundException {
        String tagValue = response.jsonPath().getString(tagKey);
        storeInPropertiesFile(tagKey, tagValue);
    }
    /**
     * This method  will store the temp data from the responses
     */
    private void storeInPropertiesFile(String key, String value) throws FileNotFoundException {
        Properties property  = new Properties();
        try{
            FileOutputStream output = new FileOutputStream("data.properties", true);
            property.setProperty(key, value);
            property.store(output, "Updated Tag Value");
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

