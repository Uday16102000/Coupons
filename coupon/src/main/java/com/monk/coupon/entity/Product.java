package com.monk.coupon.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "PRODUCT")
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "PRODUCT_ID")
	    private Long productId;

	    @Column(name = "PRODUCT_NAME", nullable = false)
	    private String productName;

	    @Column(name = "QUANTITY", nullable = false)
	    private Integer quantity;

	    @Column(name = "PRICE", nullable = false)
	    private Double price;

	    @Column(name = "IS_DELETED", nullable = false)
	    private Boolean isDeleted = false;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "PRODUCT_COUPON",
	        joinColumns = @JoinColumn(name = "PRODUCT_ID"),
	        inverseJoinColumns = @JoinColumn(name = "COUPON_ID")
	    )
	    private Set<Coupon> coupons;

	    // Getters and Setters

	    public Long getProductId() {
	        return productId;
	    }

	    public void setProductId(Long productId) {
	        this.productId = productId;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public Set<Coupon> getCoupons() {
	        return coupons;
	    }

	    public void setCoupons(Set<Coupon> coupons) {
	        this.coupons = coupons;
	    }

		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
	    
	    
}
