package com.monk.coupon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.coupon.exception.MonkException;
import com.monk.coupon.messages.MonkSuccessMessage;
import com.monk.coupon.model.ProductRequest;
import com.monk.coupon.responses.ParentResponse;
import com.monk.coupon.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	
	@Autowired
	private ProductService productService;
	
	@PostMapping("")
	public ResponseEntity<ParentResponse> addNewProduct(@RequestBody ProductRequest productRequest) throws MonkException {
		log.info("request received for creating new coupon");

		ParentResponse parentResponse = new ParentResponse() {
		};
	    productService.saveProduct(productRequest);
		parentResponse.setMessage(MonkSuccessMessage.PRODUCT_SAVED_SUCCESSFULLY);
		parentResponse.setCode(HttpStatus.OK.value());

		log.info("request ended for creating new coupon ended");

		return ResponseEntity.ok(parentResponse);
	}

}
