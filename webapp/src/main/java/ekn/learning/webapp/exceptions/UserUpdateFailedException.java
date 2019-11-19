package ekn.learning.webapp.exceptions;

public class UserUpdateFailedException extends RuntimeException {
	
	public UserUpdateFailedException(int id) {
	     super("Update failed for customer with id: " + id);
	}
}
