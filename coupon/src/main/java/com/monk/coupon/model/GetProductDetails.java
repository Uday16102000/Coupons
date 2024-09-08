package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;

public class GetProductDetails {

	@JsonProperty(value="product_id")
	private Long productId;
	
	@JsonProperty(value="quantity")
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public GetProductDetails() {
		super();
	}

	public GetProductDetails(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
	
}
