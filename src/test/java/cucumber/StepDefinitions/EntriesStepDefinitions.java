package cucumber.StepDefinitions;

import io.cucumber.java.en.And;
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

    private Map<Object, Object> map2;

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

    @And("Send request with parameters")
    public void verifyContent() {
        response = requestWithParameters(Map.of("Category","Animals"));
        System.out.println(response.body().prettyPrint());
    }

    @Then("Verify entries count is {int} and fields of entries are not empty")
    public void verifyEntriesCountAndFields(int expectedEntriesCount) {
        Assert.assertEquals(count, expectedEntriesCount);
//        for (Map.Entry<Object, Object> objectObjectEntry : map2.entrySet()) {
//            Assert.assertNotNull(objectObjectEntry.getValue());
//        }
    }

    private static <K, V> Response requestWithParameters(Map map) {
        System.out.println("Response with parameters is : ");
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get(ENTRIES_URL);
    }

}
