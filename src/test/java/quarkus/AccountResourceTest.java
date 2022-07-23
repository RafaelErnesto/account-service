package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class AccountResourceTest {

    @Test
    public void testRetrieveAllAccounts() {
        Response result =
                given()
                        .when().get("/accounts")
                        .then()
                        .statusCode(200)
                        .body(
                                containsString("George Baird"),
                                containsString("Mary Taylor"),
                                containsString("Diana Rigg")
                        )
                        .extract()
                        .response();
    }
}