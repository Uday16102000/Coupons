package com.monk.coupon.service;

import java.util.List;

import com.monk.coupon.exception.MonkException;
import com.monk.coupon.model.ApplicableCouponRequest;
import com.monk.coupon.model.ApplicableCouponResponse;
import com.monk.coupon.model.CouponRequest;

import jakarta.validation.Valid;

public interface CouponService {

	void createNewCoupon( CouponRequest couponRequest) throws MonkException;

	List<CouponRequest> getAllCoupon();

	CouponRequest fetchCoupon(Long couponId);

	ApplicableCouponResponse getAllApplicableCoupons(@Valid ApplicableCouponRequest applicableCouponRequest);

}
