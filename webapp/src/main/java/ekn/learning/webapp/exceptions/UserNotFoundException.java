package ekn.learning.webapp.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("The searched for Id is not found : " + id);
    }

}