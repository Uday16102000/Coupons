package com.monk.coupon.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(Include.NON_DEFAULT)
public class ResponsePayload<T> {

	@JsonProperty(value = "records")
	private final List<T> records = new ArrayList<>();

	@JsonProperty(value = "message")
	private String message;

	@JsonProperty(value = "error_message")
	private String errorMessage;

	public boolean addToPayload(final T t) {
		return this.records.add(t);
	}

	public boolean addAllToPayload(final Collection<T> c) {
		return this.records.addAll(c);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<T> getRecords() {
		return records;
	}

	

}
