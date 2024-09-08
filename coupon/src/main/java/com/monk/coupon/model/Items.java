package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class Items {

	@NotNull(message="Product Id Cannot Be Null")
	@JsonProperty(value = "product_id")
	private Long product_id;
	
	@NotNull(message="Quantity Cannot Be Null")
	@JsonProperty(value = "quantity")
	private Integer quantity;

	@NotNull(message="Price Cannot Be Null")
	@JsonProperty(value = "price")
	private Double price;

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
