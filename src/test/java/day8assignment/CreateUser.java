package day8assignment;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void testCreateUser(ITestContext context) {
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("location", "France");
		data.put("phone", faker.phoneNumber().cellPhone());
		String coursesArr[] = {"Java","Selenium"};
		data.put("courses", coursesArr);
		
		
		String id = given().contentType("application/json").body(data.toString())
		.when().post("http://localhost:3000/students").jsonPath().getString("id");
		
		System.out.println("Generated id is: "+id);
		context.getSuite().setAttribute("user_id", id);
		
		
		
		
		
	}
}
