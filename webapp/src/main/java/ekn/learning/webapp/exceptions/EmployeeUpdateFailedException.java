package ekn.learning.webapp.exceptions;

public class EmployeeUpdateFailedException extends RuntimeException {
	
	public EmployeeUpdateFailedException(int id) {
	     super("Update failed for customer with id: " + id);
	}
}
