package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class DeleteUser {
	
	@Test
	void test_DeleteUser(ITestContext context){
		
		//int id = (int) context.getAttribute("user_id");//this should come from createUser Request//this will come from createUser request
		int id = (int) context.getSuite().getAttribute("user_id");
		
		given().header("Authorization","Bearer "+context.getSuite().getAttribute("bearerToken")).pathParam("id", id)
		
		.when().delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then().statusCode(204)
		.log().all();
		
		
	}

}
