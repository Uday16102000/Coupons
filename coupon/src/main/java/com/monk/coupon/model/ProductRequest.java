package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

	@JsonProperty(value = "product_name")
	@NotBlank(message = "Product Name Cannot Be Blank")
	private String productName;

	@NotNull(message = "Product Price Cannot Be Null")
	@JsonProperty(value = "price")
	private Double price;

	@NotNull(message = "Quantity Cannot Be Null")
	@JsonProperty(value = "quantity")
	private Integer quantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductRequest(@NotBlank(message = "Product Name Cannot Be Blank") String productName,
			@NotNull(message = "Product Price Cannot Be Null") Double price,
			@NotNull(message = "Quantity Cannot Be Null") Integer quantity) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public ProductRequest() {
		super();
	}

	
	
	

}
