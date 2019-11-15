package ekn.learning.webapp.exceptions;

public class EmployeeDeleteFromDbFailedException extends RuntimeException {
	public EmployeeDeleteFromDbFailedException(int id) {
		super("Tried deleting employee with id: " + id + " but the operation failed");
	}
}
