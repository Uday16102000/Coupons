package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class CouponRequest {
	
	@JsonProperty(value="type")
	@NotBlank(message="Coupon Type Cannot Be Blank")
    @Pattern(regexp = "cart-wise|product-wise|bxgy", message = "Invalid Coupon Type")
	private String couponType;
	
	
	@JsonProperty(value="name")
	@NotBlank(message="Coupon Name Cannot Be Blank")
	private String couponName;
	
	@NotNull(message="Coupon Details Cannot Be null")
	@JsonProperty(value="details")
	private CouponDetails couponDetails;


	public String getCouponType() {
		return couponType;
	}


	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}


	public CouponDetails getCouponDetails() {
		return couponDetails;
	}


	public void setCouponDetails(CouponDetails couponDetails) {
		this.couponDetails = couponDetails;
	}


	public CouponRequest(String couponType, CouponDetails couponDetails) {
		super();
		this.couponType = couponType;
		this.couponDetails = couponDetails;
	}


	public CouponRequest() {
		super();
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public CouponRequest(
			@NotBlank(message = "Coupon Type Cannot Be Blank") @Pattern(regexp = "cart-wise|product-wise|bxgy", message = "Invalid Coupon Type") String couponType,
			@NotBlank(message = "Coupon Name Cannot Be Blank") String couponName,
			@NotNull(message = "Coupon Details Cannot Be null") CouponDetails couponDetails) {
		super();
		this.couponType = couponType;
		this.couponName = couponName;
		this.couponDetails = couponDetails;
	}
	
	
	
	
	
	
	

}
