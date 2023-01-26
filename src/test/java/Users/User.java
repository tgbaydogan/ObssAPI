package Users;

import common.APITestCase;
import common.CommonVariables;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class User extends APITestCase {
    CommonVariables commonVariables = new CommonVariables();
    public static String createdUserId;
    public static String username;

    @Test(priority = 1)
    public void createSingleUser() {
        String body = generateStringFromResource("src/test/resources/user/createUser.json");
        Response response = given().header("secret_key", commonVariables.SECRET_KEY)
                .contentType(commonVariables.APPLICATION_JSON).body(body).when()
                .post(commonVariables.USER_ENDPOINT).then()
                .statusCode(commonVariables.SUCCESSFUL_STATUS_CODE).log().body().extract().response();
        createdUserId = response.path("id");
        username = response.path("username");
        Assert.assertEquals(response.getStatusCode(), commonVariables.SUCCESSFUL_STATUS_CODE);
    }

    @Test(priority = 2)
    public void createSameUser() {
        String body = generateStringFromResource("src/test/resources/user/createUser.json");
        Response response = given().header("secret_key", commonVariables.SECRET_KEY)
                .contentType(commonVariables.APPLICATION_JSON).body(body).when()
                .post(commonVariables.USER_ENDPOINT).then()
                .statusCode(commonVariables.INTERNAL_SERVER_ERROR).log().body().extract().response();
        Assert.assertEquals(response.getStatusCode(), commonVariables.INTERNAL_SERVER_ERROR);
    }

    @Test(priority = 3)
    public void getCreatedUser() {
        Response response = given().header("secret_key", commonVariables.SECRET_KEY)
                .contentType(commonVariables.APPLICATION_JSON).when()
                .get(commonVariables.USER_ENDPOINT + "/" + createdUserId).then()
                .statusCode(commonVariables.SUCCESSFUL_STATUS_CODE).log().body().extract().response();
        String id = response.path("id");
        Assert.assertEquals(id, createdUserId);
    }
}
