package ekn.learning.webapp.cucumber.definitions;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static ekn.learning.webapp.helpers.TestHelper.*;
import ekn.learning.webapp.model.Message;
import ekn.learning.webapp.model.User;
import ekn.learning.webapp.services.ServiceResponseMessage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MessageControllerSteps {

	
	//Under test the default Port for the API is not 8080, but a random one
	@LocalServerPort
	private int port;
	
	private String url;
	private Message message = new Message();
	private Response response;
	
	@Before
	public void beforeScenario() {
		//Make sure that the correct port is used by RestAssured
		RestAssured.port = port;
	}
	
	@Given("the user of the message service wants to send a request to {string}")
	public void the_user_of_the_message_service_wants_to_send_a_request_to(String url) {
	   this.url = url; 
	}

	@Given("provides the id of a message with sender_id: {int}, recipient_id: {int}, message contents: {string}")
	public void provides_the_id_of_a_message_with_sender_id_recipient_id_message_contents(Integer senderId, Integer recipientId, String messageText) {
		message.setSenderId(senderId);
		message.setRecipientId(recipientId);
		message.setMessageText(messageText);
		
		int generatedId = 
			    given().
					contentType("application/json").
					body(message).
				when().
					post(url).
					as(Message.class).
					getId();
		
		message.setId(generatedId);
		url = url + "/" + generatedId;	
		
	}
	
	@Given("provides valid message contents: sender_id: {int}, recipient_id: {int}, message contents: {string}")
	public void provides_valid_message_contents_sender_id_recipient_id_message_contents(Integer senderId, Integer recipientId, String messageText) {
		message.setSenderId(senderId);
		message.setRecipientId(recipientId);
		message.setMessageText(messageText);
	}
	
	@Given("provides the an invalid id with value {int}")
	public void provides_the_an_invalid_id_with_value(Integer messageId) {
		url = url + "/" + messageId;
	}
	
	@Given("provides a message with wrong values for {int} and\\/or {int}")
	public void provides_a_message_with_wrong_values_for_and_or(Integer senderId, Integer recipientId) {
	   message.setSenderId(senderId);
	   message.setMessageText("Text");
	   message.setRecipientId(recipientId);
	}

	@When("the user does a GET request")
	public void the_user_does_a_GET_request() {
		response = get(url);
	}
	
	@When("the user does a POST request")
	public void the_user_does_a_POST_request() {
		response = 
				 given().
					contentType("application/json").
					body(message).
				when().
					post(url);
	}
	
	@When("the user does a DELETE request")
	public void the_user_does_a_DELETE_request() {
	    response = delete(url);
	}
	
	@When("the user does a PUT request")
	public void the_user_does_an_UPDATE_request() {
		response = 
				 given().
					contentType("application/json").
					body(message).
				when().
					put(url);
	}

	
	@Then("the message service returns statuscode {int}")
	public void the_message_service_returns_statuscode(Integer statusCode) {
	   assertTrue(statusCode == response.getStatusCode());
	}

	@Then("the message contains the same details")
	public void the_message_contains_the_same_details() {
	    assertTrue(compareMessage(message, response.as(Message.class), false));
	}
	
	@Then("the response message contains the same details")
	public void the_response_message_contains_the_same_details() {
		message.setId(response.as(Message.class).getId());
		assertTrue(compareMessage(message, response.as(Message.class), false));
	}
	
	@Then("the message service returns a ServiceResponseMessage with operation {string}")
	public void the_message_service_returns_a_ServiceResponseMessage_with_operation(String operation) {
	    assertEquals(operation, response.as(ServiceResponseMessage.class).getOperation());
	}
	
	@Then("He will find {int} items with ids {int} {int} and {int}")
	public void he_will_find_items_with_ids_and(Integer int1, Integer id1, Integer id2, Integer id3) {
		response.then().assertThat().body("id", hasItems(id1, id2, id3));
	}

	@Then("he will find {int} item with id {int}")
	public void he_will_find_item_with_id(Integer int1, Integer id) {
		response.then().assertThat().body("id", hasItems(id) );
	}
}
