package com.datastatistics.ford.exception;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.datastatistics.ford.commons.ApplicationConstants;

/**
 * Exception handler class for the application, contains different method for handling different types
 * of exception thrown by the application, which returns the customized response with respective error
 * messages.
 * @author Monika Tantia
 *
 */
@ControllerAdvice
public class FordExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger LOGGER = LogManager.getLogger(this.getClass());
	/**
	 * Exception handler for handling all exceptions other than FordDataNotFoundException
	 * and MethodArgumentNotValidException
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(ApplicationConstants.EXCEPTION_OCC, ex);
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Exception handler for handling exception when requested data is not found in the system.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FordDataNotFoundException.class)
	public final ResponseEntity<Object> handleDataNotFoundException(FordDataNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(ApplicationConstants.EXCEPTION_OCC, ex);
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception handler for handling excpetion when request input parameters are not valid
	 * @param ex
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		LOGGER.error(ApplicationConstants.EXCEPTION_OCC, ex);
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
