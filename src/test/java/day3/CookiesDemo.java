package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
public class CookiesDemo {

	//@Test(priority=1)
	void testCookies() {
		
		given()
		.when().get("https://www.google.com/")
		.then()
		.cookie("AEC","AaJma5s2s6neLm8wNbbKLY8BEj9B8pGFpi2wA1hiIaPlcL0lK9e-mjyBug\r\n")
		.log().all();
		
	}
	
	@Test(priority=2)
	void getCookiesInfo() {
	   Response	res = given()
		.when().get("https://www.google.com/");
	   
	   //get single cookie value
//	   String cookie_value = res.getCookie("AEC");
//	   System.out.println("THE VALUE OF COOKIE IS =====> "+cookie_value);
	   
	   //get all cookies info
	   Map<String,String> cookiesValues = res.getCookies();
	   
	  // System.out.println(cookiesValues.keySet());
	   
	   for(String k:cookiesValues.keySet()) {
		   String cookie_value = res.getCookie(k);
		   System.out.println("The value of cookie "+k+" is ===> "+cookie_value);
	   }
	   
	}
}
