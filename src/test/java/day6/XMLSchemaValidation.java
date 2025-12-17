package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation() {
		
		given()
		.when().get("https://mpe60af06e0b91192291.free.beeceptor.com/data")
		.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("bookstore.xsd"));
		
	}
	
}
