package common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class APITestCase {
    CommonVariables commonVariables = new CommonVariables();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = commonVariables.BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test Thread Number Is " + Thread.currentThread().getId());
        // if parallel run wanted add testngs suit  parallel="tests" thread-count="4"
    }

    public static String generateStringFromResource(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
