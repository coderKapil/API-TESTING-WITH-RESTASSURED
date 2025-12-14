package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Logging {

	@Test(priority=1)
			void testLogs() {
				
				given()
				.when().get("https://httpbin.org/cookies/set?session=abc123")
				.then()
				//.log().all()
				//.log().body();
				//.log().cookies();
				.log().headers();
				
			}
}
