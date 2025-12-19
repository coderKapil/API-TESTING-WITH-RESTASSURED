package day8assignment;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void testUpdateUser(ITestContext context) {
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("location", "USA");
		data.put("phone", faker.phoneNumber().cellPhone());
		String coursesArr[] = {"Rust","Postman"};
		data.put("courses", coursesArr);
		
		String id =  (String) context.getSuite().getAttribute("user_id");
		 given().contentType("application/json").body(data.toString()).pathParam("id", id)
		.when().put("http://localhost:3000/students/{id}")
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
}
