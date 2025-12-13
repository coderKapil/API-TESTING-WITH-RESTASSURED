package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
/*
 Different ways to create POST request in RestAsssured
 1)HashMap
 2)using org.json library
 3)using POJO class (Plain old java object)
 4)Using External JSON File
 */
public class DiffWaysToCreatePostRequestBody {

	String id ;
	//1)POST Request using HashMap
	//@Test(priority=1)
	void testPostUsingHashMap() {
		HashMap data = new HashMap();
		//storing data inside hashmap
		data.put("name", "Paul");
		data.put("location", "USA");
		data.put("phone", "1234567890");
		String coursesArr[] = {"C#","Ruby"};
		data.put("courses", coursesArr);
		
		id = given().contentType("application/json").body(data)
		.when().post("http://localhost:3000/students").jsonPath().getString("id");	
//		.then()
//		.statusCode(201)
//		.body("name",equalTo("Paul"))
//		.body("location",equalTo("USA"))
//		.body("phone",equalTo("1234567890"))
//		.body("courses[0]",equalTo("C#"))
//		.body("courses[1]",equalTo("Ruby"))
//		.header("Content-Type", "application/json")
//		.log().all();
		
	}
	
	//2)POST Request using org.json library add org.json dependency in pom.xml
	//@Test(priority=1)
	void testPostUsingJsonLibrary() {
				
	JSONObject data = new JSONObject();
	data.put("name", "Paul");
	data.put("location", "USA");
	data.put("phone", "1234567890");
	String coursesArr[] = {"C#","Ruby"};
	data.put("courses", coursesArr);
	id = given().contentType("application/json").body(data.toString())
	.when().post("http://localhost:3000/students").jsonPath().getString("id");	
//	.then()
//	.statusCode(201)
//	.body("name",equalTo("Paul"))
//	.body("location",equalTo("USA"))
//	.body("phone",equalTo("1234567890"))
//	.body("courses[0]",equalTo("C#"))
//	.body("courses[1]",equalTo("Ruby"))
//	.header("Content-Type", "application/json")
//	.log().all();
				
	}
	
	//3)POST Request using POJO Class
	//@Test(priority=1)
	void testPostUsingPojoClass() {
		
	Pojo_PostRequest data = new Pojo_PostRequest();
	data.setName("Scott");
	data.setLocation("France");
	data.setPhone("1234567890");
	String coursesArr[] = {"c#","java"};
	data.setCourses(coursesArr);
 
 id = given().contentType("application/json").body(data)
 .when().post("http://localhost:3000/students").jsonPath().getString("id");	
//		.then()
//		.statusCode(201)
//		.body("name",equalTo("Scott"))
//		.body("location",equalTo("France"))
//		.body("phone",equalTo("1234567890"))
//		.body("courses[0]",equalTo("c#"))
//		.body("courses[1]",equalTo("java"))
//		.header("Content-Type", "application/json")
//		.log().all();
					
	}
	
	//4)POST Request using external JSON File 
		@Test(priority=1)
		void testPostUsingExternalJsonFile() throws FileNotFoundException {
			
		//to open the file create a FILE type object
		File f = new File(".\\body.json");
		//to read the data from file
		FileReader fr = new FileReader(f);
		//to extract the data from file in json format
		JSONTokener jt = new JSONTokener(fr);
		
		//now we will use JSONObject
		JSONObject data = new JSONObject(jt);
	 
     id = given().contentType("application/json").body(data.toString())
  	 .when().post("http://localhost:3000/students").jsonPath().getString("id");
//	 .then()
//	 .statusCode(201)
//	 .body("name",equalTo("Scott"))
//	 .body("location",equalTo("France"))
//	 .body("phone",equalTo("1234567890"))
//	 .body("courses[0]",equalTo("c#"))
//	 .body("courses[1]",equalTo("java"))
//	 .header("Content-Type", "application/json")
//	 .log().all();
						
		}
	
	
	
	//deleting student record
	@Test(priority=2)
	void testDelete() {
		given()
		.when().delete("http://localhost:3000/students/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	
	
}
