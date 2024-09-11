package com.monk.coupon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ApplicableCouponDetails {
	
	@JsonProperty(value="coupon_id")
	private Long couponId;
	
	@JsonProperty(value="type")
	private String type;
	
	@JsonProperty(value="discount")
	private Double discount;

	@JsonProperty(value="offer")
	private String bxgyOffer;
	
	
	public String getBxgyOffer() {
		return bxgyOffer;
	}

	public void setBxgyOffer(String bxgyOffer) {
		this.bxgyOffer = bxgyOffer;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	

}
