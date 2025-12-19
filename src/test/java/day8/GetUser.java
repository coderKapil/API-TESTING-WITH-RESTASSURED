package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void testGetUser(ITestContext context) {
		
//		int id = (int) context.getAttribute("user_id");//this should come from createUser Request
		int id = (int) context.getSuite().getAttribute("user_id");//this should available at suite level
		
		given().header("Authorization","Bearer "+context.getSuite().getAttribute("bearerToken")).pathParam("id", id)
		
		.when().get("https://gorest.co.in/public/v2/users/{id}")
		
		.then().statusCode(200).log().all();
		
	}

}
