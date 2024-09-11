package com.monk.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Cart {
	
	@NotEmpty(message="Cart  Items Cannot Be Empty")
	@JsonProperty(value="items")
	List<Items> items;

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	

}
