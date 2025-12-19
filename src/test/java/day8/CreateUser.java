package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void testCreateUser(ITestContext context) {
		
		//faker is used to genearte dynamic random data
		Faker faker = new Faker();
		
		//JSONObject we are using here to pass the data at runtime as a body for creating user
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender",faker.demographic().sex());//gender
		data.put("email", faker.internet().emailAddress());
		// generates either "active" OR "inactive" (only one at a time)
		data.put("status", faker.options().option("active", "inactive"));
		
		String bearerToken = System.getenv("GOREST_TOKEN");

        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new RuntimeException("GOREST_TOKEN is not set in environment variables");
        }
		
		int id = given()
		.header("Authorization","Bearer "+bearerToken).contentType("application/json").body(data.toString())
		.when().post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
		
		System.out.println("Genereated id is: "+id);
		//context.setAttribute("user_id", id);//this will made id attribute available to other class test method also
		context.getSuite().setAttribute("user_id", id);//to make this variable available throughout the Suite for diff test
        context.getSuite().setAttribute("bearerToken", bearerToken);
	}
	
}
