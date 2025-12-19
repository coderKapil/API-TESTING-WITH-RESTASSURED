package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void test_UpdateUser(ITestContext context) {
		
//		int id = (int) context.getAttribute("user_id");//this should come from createUser Request;//this should come from createUser Request; //this will come from createUser request
		int id = (int) context.getSuite().getAttribute("user_id");
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", faker.demographic().sex());
		data.put("email", faker.internet().emailAddress());
		data.put("status", faker.options().option("active","inactive"));
		
		given().header("Authorization","Bearer "+context.getSuite().getAttribute("bearerToken")).contentType("application/json")
		.pathParam("id", id).body(data.toString())
		
		.when().put("https://gorest.co.in/public/v2/users/{id}")
		
		.then().statusCode(200).log().all();
		
	}
	
}
