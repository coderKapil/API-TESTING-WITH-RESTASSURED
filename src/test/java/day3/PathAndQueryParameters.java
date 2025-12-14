package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

	@Test
	void testQueryAnfPathParameters() {
//https://jsonplaceholder.typicode.com/comments?postId=1
		given()
		.pathParam("mypath", "comments")//path parameter
		.queryParam("postId",2)//query parameter
		
		.when().get("https://jsonplaceholder.typicode.com/{mypath}")
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
