package ekn.learning.webapp.exceptions;

public class MessageNotFoundForUserException extends RuntimeException {
	
	public MessageNotFoundForUserException(int id) {
		super("Could not find any messages for the user with id: " + id);
	}
	
}
