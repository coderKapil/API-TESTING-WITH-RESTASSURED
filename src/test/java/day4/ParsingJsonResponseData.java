package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	//@Test(priority=1)
	void testJsonResponse() {
		
		//Approach 1
		/*given().contentType("application/json")
		.when().get("http://localhost:3000/books")
		.then()
		.statusCode(200)
		.header("Content-Type","application/json")
		.body("[4].title",equalTo("Deep Work"));*/
		
		//Approach 2
		
		Response res = given().contentType("application/json")
		.when().get("http://localhost:3000/books");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		//value value of jsonpath
		String bookname = res.jsonPath().get("[4].title").toString();
		
		Assert.assertEquals(bookname, "Deep Work");
		
		
	}
	
	@Test(priority=2)
	void testJsonResponseBody() {
		
		//Approach 3
		
		Response res = given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/books");
 /*
  This approach is helpful when your API response contains a JSON object that has
   a JSON array inside it.
   Example  
   
   {
  "books": [
    {
      "id": "1",
      "title": "Clean Code",
      "author": "Robert C. Martin"
    },
    {
      "id": "2",
      "title": "Effective Java",
      "author": "Joshua Bloch"
    }
  ]
}

  */
		//JSONObject JSONObject jo = new JSONObject(res.toString());
		//converting response to json object type 
//		for(int i=0; i<jo.getJSONArray("books").length(); i++) { 
//			String bookTitle = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
//			System.out.println(bookTitle); 
//			}
		

		    // Step 1: Convert response body to JSONArray
		    JSONArray jsonArray = new JSONArray(res.asString());
//Search for title of the book in JSON
		  /*  boolean status = false;
		    // Step 2: Extract JSONObject from array
		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject jsonObject = jsonArray.getJSONObject(i);

		        String bookTitle = jsonObject.get("title").toString();
		       // System.out.println(bookTitle);
		        
		        if(bookTitle.equals("Deep Work")) {
		        	status  = true;
		        	break;//this will immediately break the for loop and stop further execution
		        }
		    }
		    //means the title is found in the object
		    //validating book title
		    Assert.assertEquals(status, true);
		 */   
		    //validate total price of books
		    
		    double totalPrice = 0;
		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject jsonObject = jsonArray.getJSONObject(i);
		        String bookPrice = jsonObject.get("price").toString();
		        totalPrice = totalPrice + Double.parseDouble(bookPrice);
		        
		    }
		    System.out.println("Total price of bookk is : "+totalPrice);
		    //validating total price
		    Assert.assertEquals(totalPrice, 4394.0);
		    
		    
		
	}
	
	
	
	
	
	
	

}
