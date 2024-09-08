package com.monk.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.monk.coupon.responses.ParentResponse;

@JsonInclude(Include.NON_NULL)
public class CouponParentResponse  extends ParentResponse{
	
	CouponRequest couprequest;

	List<CouponRequest> couponRequest;

	public List<CouponRequest> getCouponRequest() {
		return couponRequest;
	}

	public void setCouponRequest(List<CouponRequest> couponRequest) {
		this.couponRequest = couponRequest;
	}

	public CouponRequest getCouprequest() {
		return couprequest;
	}

	public void setCouprequest(CouponRequest couprequest) {
		this.couprequest = couprequest;
	}
	
	
	
}
