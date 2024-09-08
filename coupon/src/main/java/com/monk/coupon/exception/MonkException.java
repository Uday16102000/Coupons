package com.monk.coupon.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class MonkException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final String message;
	private final HttpStatus statusCode;

	public MonkException(final String message, final HttpStatus statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
