package com.mmi.mmi.config.exception;

import java.text.ParseException;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.hibernate.UnresolvableObjectException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mmi.mmi.model.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleEntityNotFound(NoSuchElementException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(UnresolvableObjectException.class)
	protected ResponseEntity<Object> handleEntityNotProcess(UnresolvableObjectException ex) {
		ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED,ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(ParseException.class)
	protected ResponseEntity<Object> handleParseException(ParseException ex) {
		ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED,ex);
		apiError.setDebugMessage(ex.getMessage());
		apiError.setMessage("Error Parsing");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleEntityException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
}
