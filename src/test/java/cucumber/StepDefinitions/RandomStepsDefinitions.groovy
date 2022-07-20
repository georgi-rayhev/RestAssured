package cucumber.StepDefinitions

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import org.junit.Assert

class RandomStepsDefinitions {

    private static final String RANDOM_URL = "https://api.publicapis.org/random"
    private Response response
    private JsonPath entriesJsonPath
    private int count
    private Map<String, String> map;

    @Given("Call Random endpoint")
    public void calRandomEndpoint() {
        response = RestAssured.given().when().get(RANDOM_URL);
    }
    @Then("Verify that Random endpoint return status code {int}")
    public void verifyThatRandomEndpointReturnStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
    }
    @When("Get count from response")
    public void getCountFromResponse() {
        entriesJsonPath = response.jsonPath();
        count = entriesJsonPath.getInt("entries.size()");
    }
    @Then("Verify that count is {int}")
    public void verifyResponseCount(int expectedCount) {
        Assert.assertEquals(expectedCount, count)
    }

    @Given("Send request with parameters to random endpoint where parameter is {} and value is {}")
    public void sendRequestWithParameters(String parameter, String value) {
        response = requestWithParameters(Map.of(parameter,value))
    }

    @When("Get response count")
    public void getCount() {
        entriesJsonPath = response.jsonPath();
        count = entriesJsonPath.getInt("entries.size()");
    }

    @Then("Verify Count is {int}")
    public void verifyResponseAndCount(int expectedCount) {
        Assert.assertEquals(count, expectedCount)
    }

    @When("Get response object")
    public void getResponseObject() {
        entriesJsonPath = response.jsonPath();
        map = entriesJsonPath.getMap("entries[0]");
    }

    @Then("Verify fields of the object are not empty")
    public void verifyFieldsAreNotEmpty () {
        for (Map.Entry<String, String> objectObjectEntry : map.entrySet()) {
            Assert.assertNotNull(objectObjectEntry.getValue());
        }
    }

    private static <K, V> Response requestWithParameters(Map map) {
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get(RANDOM_URL);
    }

}