package com.monk.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GET_PRODUCT")
public class GetProdcut {

	@Id
	@Column(name = "GETPRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "COUPON_ID")
	private Coupon coupon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public GetProdcut(Long id, Long productId, Integer quantity, Coupon coupon) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.coupon = coupon;
	}

	public GetProdcut() {
		super();
	}

}
