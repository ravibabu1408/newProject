package newProject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class test1 {

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
			//extract() method is used to extract complete response body.
			
			String response=given()
			.log().all()
			.queryParams("key","qaclick123")
			.header("Content-Type","application/json")
			.body(PayLoad.addPlace())
			.when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200)
			.header("Server","Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
			
			System.out.println(response);
			
			JsonPath js = new JsonPath(response);
			 System.out.println(js.getString("scope"));
			
		}

	}
