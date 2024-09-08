package com.monk.coupon.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CouponDetails {

	@JsonProperty(value = "threshold")
	private Double threshold;
	
	@JsonProperty(value = "discount")
	private Double discount;
	
	@JsonProperty(value = "product_id")
	private Long productId;
	
	@JsonProperty(value="buy_products")
	private List<BuyProductDetails> buyProduct;
	
	@JsonProperty(value="get_products")
	private List<GetProductDetails> getProducts;
	
	@JsonProperty(value="repetition_limit")
	private Integer repetitionLimit;
	
	@JsonProperty(value="coupon_condition")
	private String couponCondition;
	
	@JsonProperty(value="expire_time")
	private LocalDateTime expireTime;

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<BuyProductDetails> getBuyProduct() {
		return buyProduct;
	}

	public void setBuyProduct(List<BuyProductDetails> buyProduct) {
		this.buyProduct = buyProduct;
	}

	public List<GetProductDetails> getGetProducts() {
		return getProducts;
	}

	public void setGetProducts(List<GetProductDetails> getProducts) {
		this.getProducts = getProducts;
	}

	public Integer getRepetitionLimit() {
		return repetitionLimit;
	}

	public void setRepetitionLimit(Integer repetitionLimit) {
		this.repetitionLimit = repetitionLimit;
	}

	public CouponDetails() {
		super();
	}



	public String getCouponCondition() {
		return couponCondition;
	}

	public void setCouponCondition(String couponCondition) {
		this.couponCondition = couponCondition;
	}



	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public CouponDetails(Double threshold, Double discount, Long productId, List<BuyProductDetails> buyProduct,
			List<GetProductDetails> getProducts, Integer repetitionLimit, String couponCondition, LocalDateTime expireTime) {
		super();
		this.threshold = threshold;
		this.discount = discount;
		this.productId = productId;
		this.buyProduct = buyProduct;
		this.getProducts = getProducts;
		this.repetitionLimit = repetitionLimit;
		this.couponCondition = couponCondition;
		this.expireTime = expireTime;
	}
	
	
	
	

}
