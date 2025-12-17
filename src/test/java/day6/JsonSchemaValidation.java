package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {

	@Test
	void jsonSchemaValidate() {
		
		given()
		.when()
		.get("http://localhost:3000/students/")
		.then()
//matchesJsonSchemaInClasspath it means matches with the file present inside src/test/resources
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studentjsonschema.json"));
	}
}
