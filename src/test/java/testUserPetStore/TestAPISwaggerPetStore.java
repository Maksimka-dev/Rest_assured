package testUserPetStore;

import api.PetShopApiSpecification;
import api.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.UsersData;
import endpoints.EndPointUserPetShop;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import utils.Log;
import java.io.File;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAPISwaggerPetStore {

    private static RequestSpecification requestSpec = PetShopApiSpecification.getRequestSpecification();
    private static  File jsonSchema = new File("src/test/resources/json/userPetJsonSchema.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Order(1)
    @Test
    public void userPostPetStoreTest() {

        String jsonBody = "";
        try {
            jsonBody = OBJECT_MAPPER.writeValueAsString(UsersData.listUser);
        } catch (JsonProcessingException e) {
            Log.error("Can't create jsonBody", e);
        }

        UserRequest.userPostRequest(jsonBody, EndPointUserPetShop.CREATE_WITH_LIST, requestSpec);

    }

    @Order(2)
    @Test
    public void userGetPetStoreTest() {

        UserRequest.userGetRequest(jsonSchema,
                EndPointUserPetShop.USER + "/" + UsersData.listUser.get(0).getUsername(), requestSpec);
        UserRequest.userGetRequest(jsonSchema,
                EndPointUserPetShop.USER + "/" + UsersData.listUser.get(1).getUsername(), requestSpec);

    }

    @Order(3)
    @Test
    public void userDeletePetStoreTest() {

        UserRequest.userDeleteRequest(EndPointUserPetShop.USER + "/" + UsersData.listUser.get(0).getUsername(),
                requestSpec);
        UserRequest.userDeleteRequest(EndPointUserPetShop.USER + "/" + UsersData.listUser.get(1).getUsername(),
                requestSpec);

    }
}
