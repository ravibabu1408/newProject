package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
// create , update, delete requests for user api

import api.payloads.User;

public class userendpoint {
	
	public static Response createuser(User payload) {
		Response response=given().body(payload)
		.when().post(route.post_url);
		
		return response;
	}
	
	public static Response readuser(String username) {
		Response response=given().pathParam("username",username)
		.when().get(route.get_url);
		
		return response;
	}

	public static Response updateuser(String username, User payload) {
		Response response=given().pathParam("username",username).body(payload)
		.when().put(route.update_url);
		
		return response;
	}
	
	public static Response deleteuser(String username) {
		Response response=given().pathParam("username",username)
		.when().delete(route.update_url);
		
		return response;
	}
}
