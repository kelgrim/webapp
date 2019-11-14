package ekn.learning.webapp.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int id) {
        super("The serached for Id is not found : " + id);
    }

}