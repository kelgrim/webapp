package ekn.learning.webapp.exceptions;

public class MessageNotFoundForUserException extends RuntimeException {
	
	public MessageNotFoundForUserException(int id) {
		super("Could not any messages for the user: ");
	}
	
}
