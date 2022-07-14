package restAssuredRequests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.groovy.util.Maps;

import java.util.Map;

public class Main {

    private static final String URL = "https://api.publicapis.org/";

    private static Response getResponseBody() {
        System.out.println("All categories are :");
        return RestAssured
                .given()
                .when()
                .get("categories");

    }

    private static void getResponseStatusCode() {
        int statusCode = RestAssured
                .given()
                .when()
                .get(URL)
                .getStatusCode();
        System.out.println("Response status code is : " + statusCode);
        RestAssured.given().when().get(URL).then().assertThat().statusCode(200);
    }

    private static void getResponseHeaders() {
        System.out.println("Headers are : ");
        for (Header headers : RestAssured
                .given()
                .when()
                .get(URL)
                .getHeaders()) {
            System.out.println(headers);
        }
    }

    private static void getResponseHeader(String value) {
        System.out.println("Header with value : " + value + " is : ");
        System.out.println(RestAssured
                .given()
                .when()
                .get(URL)
                .getHeader(value));
    }

    private static <K, V> Response requestWithParameters(Map map) {
        System.out.println("Response with parameters is : ");
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get("entries");
    }

    private static <K, V> Response requestWithBody(Map map) {
        System.out.println("Request with headers is : ");
        return RestAssured
                .given()
                .headers(map)
                .when()
                .get("categories");
    }

    public static void main(String[] args) {

        RestAssured.baseURI = URL;

        Response getRequestBody = getResponseBody();
        System.out.println(getRequestBody.asString());


        getResponseStatusCode();

        getResponseHeaders();

        getResponseHeader("Content-Length");
        getResponseHeader("Content-type");

        Response response = requestWithParameters(Maps.of(
                "category", "Animals"));
        response.body().prettyPrint();

        Response response2 = requestWithParameters(Maps.of(
                "category", "Animals",
                "description","fish"));
        response2.body().prettyPrint();

        Response responseHeaders = requestWithBody(Maps.of(
                "Content-type", "application/json"));
        responseHeaders.body().prettyPrint();
//    }
    }
}
