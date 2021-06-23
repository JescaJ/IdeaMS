package com.flyhub.ideamanagementsystem.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flyhub.ideamanagementsystem.response.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		String errorMessageDesc = ex.getLocalizedMessage();
		
		if(errorMessageDesc == null) errorMessageDesc = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDesc);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
