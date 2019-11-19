package ekn.learning.webapp.exceptions;

public class UserDeleteFromDbFailedException extends RuntimeException {
	public UserDeleteFromDbFailedException(int id) {
		super("Tried deleting user with id: " + id + " but the operation failed");
	}
}
