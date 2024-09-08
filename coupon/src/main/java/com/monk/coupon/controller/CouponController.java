package com.monk.coupon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.coupon.exception.MonkException;
import com.monk.coupon.messages.MonkSuccessMessage;
import com.monk.coupon.model.ApplicableCouponRequest;
import com.monk.coupon.model.ApplicableCouponResponse;
import com.monk.coupon.model.CouponParentResponse;
import com.monk.coupon.model.CouponRequest;
import com.monk.coupon.model.CouponResponse;
import com.monk.coupon.responses.ParentResponse;
import com.monk.coupon.service.CouponService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class CouponController {
	private static final Logger log = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	private CouponService couponService;

	@PostMapping("coupons")
	public ResponseEntity<ParentResponse> createNewCoupon(@RequestBody @Valid CouponRequest couponRequest)
			throws MonkException {
		log.info("request received for creating new coupon");

		ParentResponse parentResponse = new ParentResponse() {
		};
		couponService.createNewCoupon(couponRequest);
		parentResponse.setMessage(MonkSuccessMessage.COUPON_SAVED_SUCCESSFULLY);
		parentResponse.setCode(HttpStatus.OK.value());

		log.info("request ended for creating new coupon ended");

		return ResponseEntity.ok(parentResponse);
	}
	
	@GetMapping("coupons")
	public ResponseEntity<ParentResponse> fetchAllCoupon()
			throws MonkException {
		log.info("request received for fetching all coupon");

		
		 List<CouponRequest>  couponRequestList= couponService.getAllCoupon();
		 if(couponRequestList.isEmpty())
		 {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
		 CouponParentResponse couponParentResponse= new CouponParentResponse();
		 couponParentResponse.setCouponRequest(couponRequestList);
		 ParentResponse parentResponse = couponParentResponse;
		parentResponse.setMessage(MonkSuccessMessage.COUPONS_FETCHED_SUCCESSFULLY);
		 
		parentResponse.setCode(HttpStatus.OK.value());

		log.info("request ended for fetching all coupon ended");

		return ResponseEntity.ok(parentResponse);
	}
	
	@GetMapping("coupons/{coupon-id}")
	public ResponseEntity<ParentResponse> fetchCoupon(@PathVariable("coupon-id") Long couponId)
			throws MonkException {
		log.info("request received for creating new coupon");

		
		 CouponRequest  couponRequestList= couponService.fetchCoupon(couponId);
		
		 CouponParentResponse couponParentResponse= new CouponParentResponse();
		 couponParentResponse.setCouprequest(couponRequestList);
		 ParentResponse parentResponse = couponParentResponse;
			parentResponse.setMessage(MonkSuccessMessage.COUPONS_FETCHED_SUCCESSFULLY);

			parentResponse.setCode(HttpStatus.OK.value());

		log.info("request ended for creating new coupon ended");

		return ResponseEntity.ok(parentResponse);
	}
	
	
	@PostMapping("applicable-coupons")
	public ResponseEntity<ParentResponse> getApplicableCoupons(@RequestBody @Valid ApplicableCouponRequest applicableCouponRequest)
			throws MonkException {
		log.info("request received for fetching applicable coupon");

		ApplicableCouponResponse applicableCouponResponse=couponService.getAllApplicableCoupons(applicableCouponRequest);
		
		 CouponParentResponse couponParentResponse= new CouponParentResponse();
		 ParentResponse parentResponse = couponParentResponse;
			parentResponse.setMessage(MonkSuccessMessage.COUPONS_FETCHED_SUCCESSFULLY);

			parentResponse.setCode(HttpStatus.OK.value());

			log.info("request ended for fetching applicable coupon");

		return ResponseEntity.ok(parentResponse);
	}
	
	

}
