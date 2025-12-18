package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {
	
	//@Test(priority=1)
	void testBasicAuthentication() {
		
		given().auth().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated",equalTo(true))
		.log().all();
		
	}
	
	//@Test(priority=2)
	void testDigestAuthentication() {
		
		given().auth().digest("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated",equalTo(true))
		.log().all();
		
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication() {
		//preemptive is a combination of basic and digest authentication
		given().auth().preemptive().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated",equalTo(true))
		.log().all();
		
	}
	
	@Test(priority=4)
	void bearerTokenAuthentication() {
		String bearerToken = "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";
		
		given().headers("Authorization","Bearer "+bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then()
		.statusCode(200);
		
	}
	
//	we are not able to find out any api for Oauth authentication so we are just learning it sSyntax
	
	
	//@Test(priority=5)
	void testOauth1Authentication() {
		
		given().auth().oauth("consumerKey", "consumerSecret", "AccessToken", "TokenSecret")//this is for oauth1 authentication
		.when().get("url")
		.then().log().all();
		
		
	}
	
	//@Test(priority=6)
    void testOauth2Authentication() {
		
		given().auth().oauth2("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")//this is for oauth2 authentication
		.when().get("https://api.github.com/users")
		.then()
		.statusCode(200)
		.log().all();
		
		
	}
	
	@Test(priority=7)
	void testApiKeyAuthentication() {
		
		//Method 1
	/*	given()
		.queryParam("appid", "95d85d8f967ed8cf8e950d03bd3db1b1")
		.when().get("https://api.openweathermap.org/data/2.5/weather?q=Delhi&lat=44.34&lon=10.99&units=metric")
		.then()
		.statusCode(200)
		.log().all();
		
		*/
		//Method 2 --> seprating path and query parameter 
		given()
		.queryParam("appid", "95d85d8f967ed8cf8e950d03bd3db1b1")
		.pathParam("mypath", "data/2.5/weather")
		.queryParam("q", "Delhi")
		.queryParam("lat", "44.34")
		.queryParam("lon", "10.99")
		.queryParam("units", "metric")
		
		.when().get("https://api.openweathermap.org/{mypath}")//define pathparam no need to define queryparam here
		.then()
		.statusCode(200)
		.log().all();
	}


	
}
