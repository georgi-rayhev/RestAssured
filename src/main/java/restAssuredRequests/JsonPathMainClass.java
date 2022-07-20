package restAssuredRequests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

public class JsonPathMainClass {
    private static final String URL = "https://api.publicapis.org/";
    
    public static void main(String[] args) {

        RestAssured.baseURI = URL;

        Response entries = RestAssured.given().when().get("entries");
        Response categories = RestAssured.given().when().get("categories");

        JsonPath entriesJsonPath = entries.jsonPath();
        JsonPath categoriesJsonPath = categories.jsonPath();

        //get String
        String category = entriesJsonPath.getString("entries[100].Category");
        System.out.println("Get String: " + category);

        //get Int
        int count = categoriesJsonPath.getInt("categories.size()");
        System.out.println("Categories count is : " + count);

        //get List
        List<String> listOfCategories = categoriesJsonPath.getList("categories");
        System.out.println("Categories are : " + listOfCategories);

        //get Boolean
        Boolean checkForBoolean = entriesJsonPath.getBoolean("entries[55].HTTPS");
        System.out.println("The boolean is : " + checkForBoolean);

        //get Object
        Object jsonObject =  entriesJsonPath.getJsonObject("entries[25]");
        System.out.println("The Object is : " + jsonObject);

        //get Map
        Map<String, String> map = entriesJsonPath.getMap("entries[0]");

        for (Map.Entry<String, String> objectObjectEntry : map.entrySet()) {
            System.out.println(objectObjectEntry);
        }
    }
}
