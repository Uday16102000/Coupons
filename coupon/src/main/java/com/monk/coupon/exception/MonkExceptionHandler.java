package com.monk.coupon.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.monk.coupon.generic.ResponsePayload;

@RestControllerAdvice
public class MonkExceptionHandler {

	@ExceptionHandler(MonkException.class)
	public <T> ResponseEntity<ResponsePayload<T>> applicationExceptionHandler(MonkException ex) {
		ResponsePayload<T> responsePayload = new ResponsePayload<>();
		responsePayload.setErrorMessage(ex.getMessage());
		return new ResponseEntity<>(responsePayload, ex.getStatusCode());
	}

}
