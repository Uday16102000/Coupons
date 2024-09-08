package com.monk.coupon.entity;


import java.time.LocalDateTime;
import java.util.Set;

import com.monk.coupon.model.GetProductDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUPON")
public class Coupon {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID")
    private Long couponId;

    @Column(name = "COUPON_TYPE", nullable = false)
    private String couponType;
    
    @Column(name = "COUPON_NAME", nullable = false)
    private String couponName;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    @Column(name = "COUPON_CONDITION")
    private String couponCondition;

    @Column(name = "THRESHOLD")
    private Double threshold;

    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "COUPON_EXPIRATION_TIME", nullable = false)
    private LocalDateTime couponExpirationTime;
    
    @Column(name = "REPETITION_LIMIT")
    private Integer repetitionLimit;

    @ManyToMany(mappedBy = "coupons")
    private Set<Product> products;

    
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private Set<BuyProduct> buyProducts;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private Set<GetProdcut> getProducts;
    // Getters and Setters

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCouponCondition() {
        return couponCondition;
    }

    public void setCouponCondition(String couponCondition) {
        this.couponCondition = couponCondition;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCouponExpirationTime() {
        return couponExpirationTime;
    }

    public void setCouponExpirationTime(LocalDateTime couponExpirationTime) {
        this.couponExpirationTime = couponExpirationTime;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

	public Integer getRepetitionLimit() {
		return repetitionLimit;
	}

	public void setRepetitionLimit(Integer repetitionLimit) {
		this.repetitionLimit = repetitionLimit;
	}

	public Set<BuyProduct> getBuyProducts() {
		return buyProducts;
	}

	public void setBuyProducts(Set<BuyProduct> buyProducts) {
		this.buyProducts = buyProducts;
	}

	public Set<GetProdcut> getGetProducts() {
		return getProducts;
	}

	public void setGetProducts(Set<GetProdcut> getProducts) {
		this.getProducts = getProducts;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
    
    
}

