package cucumber.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;

public class CategoriesStepDefinitions {

    private static final String CATEGORIES_URL = "https://api.publicapis.org/categories";
    private Response response;
    private int count;
    private JsonPath categoriesJsonPath;
    private List<String> listOfCategories;

    @Given("Call Categories endpoint")
    public void callCategoriesEndpoint() {
        response = RestAssured.given().when().get(CATEGORIES_URL);
    }

    @Then("Verify that response have status code {int}")
    public void verifyThatResponseHaveStatusCodeAndCategoriesCountAre(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @When("Get count of categories")
    public void getCountOfCategories() {
        categoriesJsonPath = response.jsonPath();
        count = categoriesJsonPath.getInt("categories.size()");
    }

    @Then("Verify that categories count are {int}")
    public void verifyThatCategoriesCountAre(int expectedCategoriesCount) {
        Assert.assertEquals(expectedCategoriesCount, count);
    }

    @When("Get all categories")
    public void getAllCategories() {
        categoriesJsonPath = response.jsonPath();
        listOfCategories = categoriesJsonPath.getList("categories");
    }

    @Then("Verify that response contains {}")
    public void verifyThatResponseContainsAllCategories(String category) {
        Assert.assertTrue(listOfCategories.contains(category));
    }
}
