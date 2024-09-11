package com.monk.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monk.coupon.responses.ParentResponse;

public class ApplicableCouponResponse  extends ParentResponse{
	
	@JsonProperty(value="applicable_coupons")
	List<ApplicableCouponDetails>  applicableCouponDetails;

	public List<ApplicableCouponDetails> getApplicableCouponDetails() {
		return applicableCouponDetails;
	}

	public void setApplicableCouponDetails(List<ApplicableCouponDetails> applicableCouponDetails) {
		this.applicableCouponDetails = applicableCouponDetails;
	}

	
	
}
