package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userendpoint;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	
  @BeforeClass
  public void setupData() {
	  faker= new Faker();
	  userPayload= new User();	
	  userPayload.setId(faker.idNumber().hashCode());
	  userPayload.setUsername(faker.name().username());
	  userPayload.setFirstname(faker.name().firstName());
	  userPayload.setEmail(faker.internet().safeEmailAddress());
	  userPayload.setPassword(faker.internet().password(5, 10));
	  userPayload.setLastname(faker.phoneNumber().cellPhone());
  }
	
  @Test(priority=1)
  public void testPostUser() {
	 Response response= userendpoint.createuser(userPayload);
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
  
  @Test(priority=2)
  public void testGeyuserByName() {
	 Response response= userendpoint.readuser(this.userPayload.getUsername());
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
  
  @Test(priority=2)
  public void testUpdateUserByName() {
	  
	  //update payload:
	  userPayload.setUsername(faker.name().username());
	  userPayload.setFirstname(faker.name().firstName());
	  userPayload.setEmail(faker.internet().safeEmailAddress());
	  
	 Response response= userendpoint.updateuser(this.userPayload.getUsername(),userPayload);
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
  
  @Test(priority=3)
  public void testDeleteUserByName() {
	  
	  
	 Response response= userendpoint.deleteuser(this.userPayload.getUsername());
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
}
