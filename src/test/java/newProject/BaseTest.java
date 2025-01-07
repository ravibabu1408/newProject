package newProject;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       
		// any automation API test should be written in this principles only.
		// given - method takes all input details what you need to submit for an API
		//when - method used to submit api with http method
		//then -- in this method we validate the response.
		
		//set your base URI
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		/* for given method we are chaining of all queryParam, log, 
		    all, body, header, authorisation method etc
		*/
		/* http method and resource name will go into when method
		 * that is when you are submitting your api with post http method on the required resource
		 * 	post() method takes the one resource in string format)
		 *  so the baseURI is concatinated with resource and query param.
		 * */
		
		given()
		.log().all()
		.queryParams("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"location\\\": {\r\n"
				+ "\"lat\": -38.383494,\" \r\n"
				+ "\"lng\\\": 33.427362\"\r\n"
				+ "}, \r\n"
				+ "\"accuracy\": 50,  \r\n"
				+ "\"name\": \"Rahul Shetty Academy\", \r\n"
				+ "\"phone_number\": \"(+91) 983 893 3937\", \r\n"
				+ "\"address\": \"50, side layout, cohen 09\",\r\n"
				+ "\"types\": [ \r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\" \r\n"
				+ "], \r\n"
				+ "\"website\": \"http://ravi.com\", \r\n"
				+ "\"language\": \"French-IN\" \r\n"
				+ "}")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server","Apache/2.4.52 (Ubuntu)");
		
	}

}
