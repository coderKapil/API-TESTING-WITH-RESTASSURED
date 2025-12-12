package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
	int id;
	@Test(priority=1)
	void getUsers() {
		//GET request
		given()
		
		.when().get("https://fakestoreapi.com/users")
		.then()
		.statusCode(200)
		.body("[1].id",equalTo(2))
		.body("[1].email", equalTo("morrison@gmail.com"))
		.log().all();
	}
	
	@Test(priority=2)
	void createUser() {
		//POST request
		HashMap data = new HashMap();
		data.put("username", "john.parlo");
		data.put("email", "john.parlo@example.com");
		data.put("password", "pass123");
		
		id = given().contentType("application/json").body(data)
		
		.when().post("https://fakestoreapi.com/users").jsonPath().getInt("id");
//		.then()
//		.statusCode(201)
//		.log().all();
		}
	
	@Test(priority=3,dependsOnMethods = ("createUser"))
	void updateUser() {
		//PUT Request
		HashMap data = new HashMap();
		data.put("username", "pablo.parlo");
		data.put("email", "pablo.parlo@example.com");
		
	    given().contentType("application/json").body(data)
		
		.when().put("https://fakestoreapi.com/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
		}
	
	@Test(priority=4,dependsOnMethods = ("createUser"))
	void deleteUser() {
		given()
		.when().delete("https://fakestoreapi.com/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	
	
	
	}
