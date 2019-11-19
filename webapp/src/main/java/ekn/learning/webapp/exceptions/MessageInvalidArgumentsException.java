package ekn.learning.webapp.exceptions;

import ekn.learning.webapp.model.Message;

public class MessageInvalidArgumentsException extends RuntimeException {
	
	public MessageInvalidArgumentsException(Message message) {
		super("Failed to insert message: " + message.toString());
	}
}
