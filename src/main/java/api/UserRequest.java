package api;
import io.restassured.specification.RequestSpecification;
import utils.ConfProperties;
import java.io.File;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class UserRequest {

    public static void userPostRequest(String jsonBody, String endpoint, RequestSpecification specification){

        given().spec(specification)
                .when()
                .header(ConfProperties.getProperty("headerName"), ConfProperties.getProperty("headerValue"))
                .body(jsonBody)
                .post(endpoint)
                .then()
                .statusCode(200);
    }

    public static void userGetRequest(File jsonSchema, String endpoint, RequestSpecification specification){

        given().spec(specification)
                .when()
                .header(ConfProperties.getProperty("headerName"), ConfProperties.getProperty("headerValue"))
                .get(endpoint)
                .prettyPeek()
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));

    }

    public static void userDeleteRequest(String endpoint, RequestSpecification specification){

        given().spec(specification)
                .when()
                .header(ConfProperties.getProperty("headerName"), ConfProperties.getProperty("headerValue"))
                .delete(endpoint)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
