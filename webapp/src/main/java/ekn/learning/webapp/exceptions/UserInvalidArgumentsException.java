package ekn.learning.webapp.exceptions;

public class UserInvalidArgumentsException extends RuntimeException  {
	 public UserInvalidArgumentsException() {
	        super("Provided parameters not correct");
	    }
}
