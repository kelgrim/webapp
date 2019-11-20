package ekn.learning.webapp.exceptions;

import ekn.learning.webapp.model.User;

public class UserInvalidArgumentsException extends RuntimeException  {
	 public UserInvalidArgumentsException(User user) {
	        super("Provided parameters not correct for the following " + user.toString());
	    }
}
