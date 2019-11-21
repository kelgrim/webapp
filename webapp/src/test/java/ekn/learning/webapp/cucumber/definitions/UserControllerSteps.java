package ekn.learning.webapp.cucumber.definitions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import ekn.learning.webapp.controller.UserController;
import ekn.learning.webapp.model.User;
import ekn.learning.webapp.services.ServiceResponseMessage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ekn.learning.webapp.helpers.TestHelper.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerSteps {
	
	//Under test the default Port for the API is not 8080, but a random one
	@LocalServerPort
	private int port;
	
	private User user = new User();
	private String url;
	//private int userId;
	//private User createdUser;
	private Response response;

	@Before
	public void beforeScenario() {
		//Make sure that the correct port is used by RestAssured
		RestAssured.port = port;
	}
	
	@Given("the user of the service wants to send a request to URL {string}")
	public void the_user_of_the_service_wants_to_send_a_request_to_URL(String url) {
	   this.url = url;
	}
	
	@Given("the user provides id {int}")
	public void wants_the_details_for_customer_with_id(Integer id) {
		url = url + "/" + id;
	}
	
	@Given("the user provides an existing id")
	public void the_user_provides_an_existing_id() {
		//Create a user to delete
		user.setUsername("deleteme");
		user.setDescription("delete description");
		user.setEmail("delete email");
	    int idToDelete = 
			    given().
					contentType("application/json").
					body(user).
				when().
					post(url).
					as(User.class).
					getId();
		url = url + "/" + idToDelete;		
	}
	
	@When("that user provides correct values for  {string}, {string} and {string}")
	public void that_user_provides_correct_values_for_and(String username, String description, String email) {
	    user.setUsername(username);
	    user.setDescription(description);
	    user.setEmail(email);
	

	}
	
	@When("that user provides an incorrect value for the username")
	public void that_user_provides_an_incorrect_value_for_the_username() {
	  user.setUsername(null);
	  user.setDescription("will fail: description");
	  user.setEmail("will fail: email");
	}
	
	@When("that user makes a POST Request")
	public void that_user_makes_a_POST_Request() {
	    response = 
	    	given().
				contentType("application/json").
				body(user).
			when().
	        	post(url);
	}
	
	@When("that user makes a GET Request")
	public void that_user_makes_a_GET_Request() {
	    response = get(url);
	}
	
	@When("that user makes a DELETE Request")
	public void that_user_makes_a_DELETE_Request() {
		response = delete(url);
	}
	
	@When("that user makes a PUT Request")
	public void that_user_makes_a_PUT_Request() {
		response =
	    	given().
				contentType("application/json").
				body(user).
			when().
	        	put(url);
	}

	@Then("the service will return a Json with that same data")
	public void the_service_will_return_a_Json_with_that_same_data() {
		User createdUser = response.as(User.class);
		assertTrue(compareUser(user, createdUser));
	}

	@Then("the service returns statuscode {int}")
	public void the_service_returns_statuscode(Integer statusCode) {
	    assertTrue(statusCode == response.getStatusCode());
	}
	
	@Then("the response contains a JSON with users including ID {int} {int} and {int}")
	public void the_response_contains_a_JSON_with_users_including_ID_and(Integer id1, Integer id2, Integer id3) {
		response.then().assertThat().body("id", hasItems(id1, id2, id3));
	}
	
	@Then("the response contains the correct values for {string}, {string} and {string}")
	public void the_response_contains_the_correct_values_for_and(String username, String description, String email) {
	    user.setUsername(username);
	    user.setDescription(description);
	    user.setEmail(email);
	    
	    User responseUser = response.as(User.class);
	    
	    assertTrue(compareUser(user, responseUser) );
	    
	}
	
	@Then("the service returns a ServiceResponseMessage with operation {string}")
	public void the_service_returns_a_ServiceResponseMessage_with_operation(String operation) {
	    // Write code here that turns the phrase above into concrete actions
	   ServiceResponseMessage srm =  response.as(ServiceResponseMessage.class);
	   assertEquals(operation, srm.getOperation() );
	}
	
	@Then("the response contains the correct values for the updated user")
	public void the_response_contains_the_correct_values_for_the_updated_user() {
	  User adjustedUser = response.as(User.class);
	  assertTrue(compareUser(user, adjustedUser));
	}
	

}
