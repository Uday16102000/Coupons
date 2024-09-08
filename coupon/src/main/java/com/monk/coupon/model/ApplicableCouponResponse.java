package com.monk.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicableCouponResponse {
	
	@JsonProperty(value="applicable_coupons")
	List<ApplicableCouponDetails>  applicableCouponDetails;

}
