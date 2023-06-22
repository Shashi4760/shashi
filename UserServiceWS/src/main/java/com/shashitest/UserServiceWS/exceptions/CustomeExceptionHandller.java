package com.shashitest.UserServiceWS.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shashitest.UserServiceWS.model.ErrorMessage;

@ControllerAdvice
public class CustomeExceptionHandller extends ResponseEntityExceptionHandler {

	// Handle Exception class
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> catchException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
				ex.getMessage());

		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Handle NullPointerException
	/*
	 * @ExceptionHandler(value = NullPointerException.class) public
	 * ResponseEntity<Object> handleNullPointerException(NullPointerException ex,
	 * WebRequest request) { ErrorMessage errorMessage = new
	 * ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),
	 * ex.getMessage());
	 * 
	 * return new ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * // Handle custome or usertype class exception
	 * 
	 * @ExceptionHandler(value = UserServiceException.class) public
	 * ResponseEntity<Object> handleUserServiceException(UserServiceException ex,
	 * WebRequest request) { ErrorMessage errorMessage = new
	 * ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),
	 * ex.getMessage());
	 * 
	 * return new ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

	// Handle more than one exception custome or usertype class exception
	@ExceptionHandler(value = { UserServiceException.class, NullPointerException.class })
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
				ex.getMessage());

		return new ResponseEntity<Object>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// validate if request data is mismatch
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
				"Total Errors- "+ex.getErrorCount()+", First Error:- "+ex.getFieldError().getDefaultMessage());
		return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
