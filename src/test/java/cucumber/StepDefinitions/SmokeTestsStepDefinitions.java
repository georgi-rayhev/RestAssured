package cucumber.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
public class SmokeTestsStepDefinitions {

    private String baseUrl;
    private int responseStatus;

    @Given("We have a base URL : {word}")
    public void getBaseUrl(String url) {
        baseUrl = url;
    }

    @When("Endpoint is {word}")
    public void getEndpoint(String endpoint) {
        RestAssured.baseURI = baseUrl + endpoint;
    }

    @And("Get response status code")
    public void getResponseStatusCode() {
        responseStatus = RestAssured
                .given()
                .when()
                .get()
                .getStatusCode();
    }

    @Then("Verify that status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, responseStatus);
    }
}
