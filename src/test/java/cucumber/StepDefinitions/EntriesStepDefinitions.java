package cucumber.StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Map;


public class EntriesStepDefinitions {

    private static final String ENTRIES_URL = "https://api.publicapis.org/entries";
    private Response response;
    private JsonPath entriesJsonPath;
    private int count;
    private Map<String, String> map;
    @When("Call Entries endpoint")
    public void callCategoriesEndpoint() {
        response = RestAssured.given().when().get(ENTRIES_URL);
    }

    @Then("Verify that entries response have status code {int}")
    public void verifyThatResponseHaveStatusCodeAndCategoriesCountAre(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @When("Get count of entries")
    public void getCountOfCategories() {
        entriesJsonPath = response.jsonPath();
        count = entriesJsonPath.getInt("entries.size()");
    }

    @Then("Verify that entries count are {int}")
    public void verifyThatCategoriesCountAre(int expectedCategoriesCount) {
        Assert.assertEquals(expectedCategoriesCount, count);
    }

    @When("Get a random record")
    public void getRandomRecord() {
        entriesJsonPath = response.jsonPath();
        map = entriesJsonPath.getMap("entries[0]");
    }

    @Then("Verify that record's fields are not empty")
    public void verifyRecordFieldsAreNotEmpty() {
        for (Map.Entry<String, String> objectObjectEntry : map.entrySet()) {
            Assert.assertNotNull(objectObjectEntry.getValue());
        }
    }

    @When("Send request with parameters to entries endpoint where parameter is {} and value is {}")
    public void sendRequestWithParameters(String parameter, String value) {
        response = requestWithParameters(Map.of(parameter,value));
    }

    @Then("Verify count is {}")
    public void verifyCount(int expectedCount) {
        Assert.assertEquals(expectedCount, count);
    }

    private static <K, V> Response requestWithParameters(Map map) {
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get(ENTRIES_URL);
    }
}
