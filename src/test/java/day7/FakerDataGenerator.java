package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	
	@Test
	void testGenerateDummyData() {
		
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		String username = faker.name().username();
		String password = faker.internet().password();
		String business = faker.business().creditCardNumber();
		String email = faker.internet().emailAddress();
		String animal = faker.animal().name();
		
		System.out.println("Full name"+fullname);
		System.out.println("First name"+firstname);
		System.out.println("Last name"+lastname);
		System.out.println("User name"+username);
		System.out.println("Password name"+password);
		System.out.println("Business name"+business);
		System.out.println("Email name"+email);
		System.out.println("Animal name"+animal);
	}
	
}
