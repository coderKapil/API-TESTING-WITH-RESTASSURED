package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {

	@Test(priority=1)
	void singleFileUpload() {
		//file obj created for defining path of the file
		File myfile = new File("C:\\Workspace\\files for api testing\\test1.txt");
//multipart is used for sending file along with post request
		given().multiPart("file",myfile).contentType("multipart/form-data")
		.when().post("http://localhost:8080/uploadFile")
		.then()
		.statusCode(200)
		.body("fileName",equalTo("test1.txt"))
		.log().all();
	}
	
	@Test(priority=2)
	void multipleFileUpload() {
		//file obj created for defining path of the file
		File myfile1 = new File("C:\\Workspace\\files for api testing\\test1.txt");
		File myfile2 = new File("C:\\Workspace\\files for api testing\\test2.txt");
//multipart is used for sending file along with post request
		given().multiPart("files",myfile1)//mention key as files for multiple files
		.multiPart("files",myfile2)
		.contentType("multipart/form-data")
		.when().post("http://localhost:8080/uploadMultipleFiles")
		.then()
		.statusCode(200)
		.body("[0].fileName",equalTo("test1.txt"))
		.body("[1].fileName",equalTo("test2.txt"))
		.log().all();
	}
	
	
	//@Test
	void multipleFileUpload2() {//this wont work for every api dependson how dev has created the api
		//if u wanted to upload more than 3 or 5 file suppose 50 files in one go then use this
		//file obj created for defining path of the file
		File myfile1 = new File("C:\\Workspace\\files for api testing\\test1.txt");
		File myfile2 = new File("C:\\Workspace\\files for api testing\\test2.txt");
		File fileArr[] = {myfile1,myfile2};
//multipart is used for sending file along with post request
		given().multiPart("files",fileArr)//mention key as files for multiple files
		.contentType("multipart/form-data")
		.when().post("http://localhost:8080/uploadMultipleFiles")
		.then()
		.statusCode(200)
		.body("[0].fileName",equalTo("test1.txt"))
		.body("[1].fileName",equalTo("test2.txt"))
		.log().all();
	}
	
	//check whether the file is successfully uploaded or not
	@Test
	void fileDownload() {
		given()
		.when().get("http://localhost:8080/downloadFile/test1.txt")//this is download url
        .then()
        .statusCode(200)
        .log().body();
	}
	
	
	
	
	
	
	
	
	
}
