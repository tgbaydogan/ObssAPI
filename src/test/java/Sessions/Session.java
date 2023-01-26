package Sessions;

import Users.User;
import common.APITestCase;
import common.CommonVariables;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

public class Session extends APITestCase {
    CommonVariables commonVariables = new CommonVariables();

    @Test(priority = 4)
    public String getSessionId() {
        Response response = given().header("secret_key", commonVariables.SECRET_KEY)
                .contentType(commonVariables.APPLICATION_JSON).when()
                .get(commonVariables.SESSION_TEMPLATES).then()
                .statusCode(commonVariables.SUCCESSFUL_STATUS_CODE).log().body().extract().response();
        JsonPath sessionResponse = new JsonPath(response.asString());
        String sessionId = sessionResponse.getString("results[0].id");
        return sessionId;
    }

    @Test(priority = 5)
    public void createSession() {
        String sessionId = getSessionId();
        System.out.println(sessionId);
        System.out.println(User.createdUserId);
        System.out.println(User.username);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DATE);
        String today = year + "-" + month + "-" + day + "T" + "00:00.00Z";
        String later = year + 1 + "-" + month + "-" + day + "T" + "00:00.00Z";

        System.out.print(today);

        System.out.print(later);

    }
}
