package ekn.learning.webapp.exceptions;

public class EmployeeInvalidArgumentsException extends RuntimeException  {
	 public EmployeeInvalidArgumentsException() {
	        super("Provided parameters not correct");
	    }
}
