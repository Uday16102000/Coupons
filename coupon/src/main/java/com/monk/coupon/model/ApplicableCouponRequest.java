package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ApplicableCouponRequest {
	@NotNull(message="Cart Cannot Be Null")
	@JsonProperty(value="cart")
	private Cart cart;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
