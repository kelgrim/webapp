package ekn.learning.webapp.exceptions;

public class MessageNotFoundException extends RuntimeException {

	public MessageNotFoundException(int id) {
		super("Message not found with id: " + id);
	}
}
