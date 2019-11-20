package ekn.learning.webapp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler({
		  UserNotFoundException.class,
		  MessageNotFoundException.class,
		  MessageNotFoundForUserException.class,	  
	  })
	  public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

	        CustomErrorResponse errors = new CustomErrorResponse();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	    }
	  
	  @ExceptionHandler({
		  UserInvalidArgumentsException.class,
		  MessageInvalidArgumentsException.class,
		  UserDeleteFromDbFailedException.class,
		  UserUpdateFailedException.class,
		  UserWriteToDbFailedException.class
	  })
	  public ResponseEntity<CustomErrorResponse> customBadArguments(Exception ex, WebRequest request){
		  CustomErrorResponse errors = new CustomErrorResponse();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.BAD_REQUEST.value());

	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	  }
	  
}