package ekn.learning.webapp.restassured;

/*
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import ekn.learning.webapp.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
*/

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeprecatedRestAssuredTest {
/*
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() throws Exception {
		//System.out.println("BEFORE IS BEING RUN");
	    RestAssured.port = port;
	}
	
	
	//Example showing how to test whether multiple items are in the collection
	@Test
	public void testExample() {
		//System.out.println("test executing");
		//System.out.println("----- Server running on port: " + port);
		//assertTrue(false);
		get("/api/v1/user").then()
		.assertThat().body("id", hasItems(1,2,3));
		
	}
	
	//Example showing how to read values from the response
	@Test
	public void getMessageBody() {
		String username = get("/api/v1/user/1").path("username");
		int id = get("/api/v1/user/1").path("id");
		System.out.println("Printing the path:");
		System.out.println(id);
		System.out.println(username);
	}
	
	//Example showing how to send map an object to the body of a request
	@Test
	public void testMapPojoToJson() {
		//ObjectMapper mapper = new ObjectMapper();
		User myUser = new User();
		//myUser.setId(4);
		myUser.setUsername("testusername");
		myUser.setDescription("description");
		myUser.setEmail("email@mail.com");
		
		given().
			contentType("application/json").
			body(myUser).
		when().
        	post("/api/v1/user").
        then().
        	assertThat().
        	body("username", equalTo("testusername"));
        	//body(equalTo("{\"status\": \"Record Added Successfully\"}"));
		
	}
	
	//Example showing how to receive a JSON and map that a POJO
	@Test
	public void testMapJsonToPojo() {
		User user = get("/api/v1/user/1").as(User.class);
		System.out.println(user.toString());
		
	}
	
	//Example showing receival of JSON with assertion on status code
	@Test
	public void testMapJsonToPojoWithAssertStatus() {
		Response response = get("/api/v1/user/1");
		
		int statusCode = response.getStatusCode();
		assertTrue(statusCode == 200);
		
		
		
				
	}
	*/
	
	
}
