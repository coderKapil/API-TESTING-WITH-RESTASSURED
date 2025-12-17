package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SerializationDeserialization {

	
	//@Test
	void convertPojoToJson() throws JsonProcessingException {
	//created java object using pojo class
		StudentPojo stupojo = new StudentPojo();
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("1234567890");
		String coursesArr[] = {"c#","java"};
		stupojo.setCourses(coursesArr);
		
	//convert java object to json object(serialization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println("converting java obj to json data "+jsonData);
		
	}
	
	
	@Test
	void convertJsonToPojo() throws JsonMappingException, JsonProcessingException {
	
		String jsonData = "{\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"1234567890\",\r\n"
				+ "  \"courses\" : [ \"c#\", \"java\" ],\r\n"
				+ "  \"name\" : \"Scott\"\r\n"
				+ "}";
		
		//convert json data --> POJO Object
		ObjectMapper objMapper = new ObjectMapper();
		StudentPojo stupobj = objMapper.readValue(jsonData, StudentPojo.class);//convert json to pojo class object
		
		System.out.println("Name: "+stupobj.getName());
		System.out.println("location: "+stupobj.getLocation());
		System.out.println("phone: "+stupobj.getPhone());
		System.out.println("Course: "+stupobj.getCourses()[0]);
		System.out.println("Course: "+stupobj.getCourses()[1]);
	}
	
	
	
	
	
	
	
}
