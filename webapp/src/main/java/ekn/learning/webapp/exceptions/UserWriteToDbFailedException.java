package ekn.learning.webapp.exceptions;

public class UserWriteToDbFailedException extends RuntimeException{
	public UserWriteToDbFailedException() {
		super("Customer could not be written to DB, check that values are correct");
	}

}
