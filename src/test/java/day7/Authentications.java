package day7;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

    //@Test(priority=1)
    void testBasicAuthentication() {

        given()
            .auth().basic("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .log().all();
    }

    //@Test(priority=2)
    void testDigestAuthentication() {

        given()
            .auth().digest("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .log().all();
    }

    @Test(priority=3)
    void testPreemptiveAuthentication() {

        given()
            .auth().preemptive().basic("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .log().all();
    }

    // ================= BEARER TOKEN =================

    @Test(priority=4)
    void bearerTokenAuthentication() {

        String bearerToken = System.getenv("GITHUB_TOKEN");

        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new RuntimeException("GITHUB_TOKEN is not set");
        }

        given()
            .header("Authorization", "Bearer " + bearerToken)
        .when()
            .get("https://api.github.com/user/repos")
        .then()
            .statusCode(200)
            .log().all();
    }

    // ================= OAUTH 1 (SYNTAX DEMO) =================

    //@Test(priority=5)
    void testOauth1Authentication() {

        given()
            .auth().oauth(
                System.getenv("OAUTH1_CONSUMER_KEY"),
                System.getenv("OAUTH1_CONSUMER_SECRET"),
                System.getenv("OAUTH1_ACCESS_TOKEN"),
                System.getenv("OAUTH1_TOKEN_SECRET")
            )
        .when()
            .get("url")
        .then()
            .log().all();
    }

    // ================= OAUTH 2 =================

    //@Test(priority=6)
    void testOauth2Authentication() {

        String oauth2Token = System.getenv("GITHUB_TOKEN");

        if (oauth2Token == null || oauth2Token.isEmpty()) {
            throw new RuntimeException("GITHUB_TOKEN is not set");
        }

        given()
            .auth().oauth2(oauth2Token)
        .when()
            .get("https://api.github.com/user")
        .then()
            .statusCode(200)
            .log().all();
    }

    // ================= API KEY =================

    @Test(priority=7)
    void testApiKeyAuthentication() {

        String apiKey = System.getenv("OPENWEATHER_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("OPENWEATHER_API_KEY is not set");
        }

        given()
            .queryParam("appid", apiKey)
            .pathParam("mypath", "data/2.5/weather")
            .queryParam("q", "Delhi")
            .queryParam("lat", "44.34")
            .queryParam("lon", "10.99")
            .queryParam("units", "metric")
        .when()
            .get("https://api.openweathermap.org/{mypath}")
        .then()
            .statusCode(200)
            .log().all();
    }
}
