package ekn.learning.webapp.cucumber.definitions;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ekn.learning.webapp.controller.UserController;
import ekn.learning.webapp.model.User;

import static ekn.learning.webapp.helpers.TestHelper.*;

@SpringBootTest
public class TestStepDefinitions {
	
	private User user = new User();
	private int userId;
	private User createdUser;

	@Autowired
	private UserController userController; // = new UserController();

	@Given("I have a valid user with {string}, {string} and {string}")
	public void i_have_a_valid_user_with_and(String username, String description, String email) {
	   user.setUsername(username);
	   user.setDescription(description);
	   user.setEmail(email);
	}
	
	@When("I post that user on the service")
	public void i_post_that_user_on_the_service() {
		createdUser = userController.addUser(user);
	}
	
	@Then("The user will be created")
	public void the_user_will_be_created() {
	    assertTrue(compareUser(user, createdUser));
	}

}
