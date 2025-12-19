package day8assignment;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void testDeleteUser(ITestContext context) {
		
		String id =  (String) context.getSuite().getAttribute("user_id");
		
		given().pathParam("id", id)
		.when().delete("http://localhost:3000/students/{id}")
		.then()
		.statusCode(200)
		.log().all();
		
	}
	

}
