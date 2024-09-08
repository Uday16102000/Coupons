package com.monk.coupon.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.monk.coupon.entity.Product;
import com.monk.coupon.exception.MonkException;
import com.monk.coupon.messages.MonkErrorMessages;
import com.monk.coupon.model.ProductRequest;
import com.monk.coupon.repository.ProductRepository;
import com.monk.coupon.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveProduct(ProductRequest productRequest) throws MonkException {
		log.info("service started for saving product");
   
		if(productRepository.existsByProductName(productRequest.getProductName()))
		{
			throw new MonkException(MonkErrorMessages.ENTER_UNIQUE_PRODUCT_NAME, HttpStatus.BAD_REQUEST);
		}
		
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product);
		productRepository.save(product);
		
		log.info("service ended for saving product");

		
		
	}

}
