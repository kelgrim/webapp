package ekn.learning.webapp.exceptions;

public class EmployeeWriteToDbFailedException extends RuntimeException{
	public EmployeeWriteToDbFailedException() {
		super("Customer could not be written to DB, check that values are correct");
	}

}
