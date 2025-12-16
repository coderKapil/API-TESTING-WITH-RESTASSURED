package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlResponseDemo {
	//Approach 1
	//@Test(priority=1)
	void XmlResponseDemo() {
		given()
		.when().get("https://mpe60af06e0b91192291.free.beeceptor.com/data")
		.then()
		.statusCode(200)
		.header("Content-Type",equalTo("application/xml"))
		.body("bookstore.book[2].title",equalTo("Design Patterns"))
		.body("bookstore.book[2].author",equalTo("Erich Gamma"))
		.log().all();
	}
	
	//Approach 2
	//@Test(priority=2)
	void testXmlResponseDemo() {
		Response res = given()
		.when().get("https://mpe60af06e0b91192291.free.beeceptor.com/data");
		
		//validation
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/xml");
	
		String book_title = res.xmlPath().get("bookstore.book[3].title").toString();
		String book_author = res.xmlPath().get("bookstore.book[3].author").toString();
		Assert.assertEquals(book_title, "Refactoring");
		Assert.assertEquals(book_author, "Martin Fowler");
	}
	
	//Approach 3
	@Test(priority=3)
	void testXmlRespUsingXmlpath() {
		
		Response res = given().when().get("https://mpe60af06e0b91192291.free.beeceptor.com/data");
		
	    XmlPath xmlobj = new XmlPath(res.asString());
	    
	    List<String> all_books = xmlobj.getList("bookstore.book");
		Assert.assertEquals(all_books.size(), 10);
		
		//printing all the book title
		List<String> book_titles = xmlobj.getList("bookstore.book.title");
		
		boolean status = false;
		for(String book_title:book_titles) {
			//printing all the book titles
			//System.out.println(book_title);
	//now i want to check whether the book title Design Patterns is present or not 
			if(book_title.equals("Spring in Action")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
	
	
	
	
	
	
	
	

}
