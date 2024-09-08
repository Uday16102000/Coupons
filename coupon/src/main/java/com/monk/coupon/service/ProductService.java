package com.monk.coupon.service;

import com.monk.coupon.exception.MonkException;
import com.monk.coupon.model.ProductRequest;

public interface ProductService {

	void saveProduct(ProductRequest productRequest) throws MonkException;

}
