package com.monk.coupon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monk.coupon.entity.BuyProduct;
import com.monk.coupon.entity.Coupon;

public interface BuyProductRepository extends JpaRepository<BuyProduct, Long>{

	List<BuyProduct> findAllByCouponIn(List<Coupon> couponList);

	List<BuyProduct> findAllByCoupon(Coupon coupon);

	List<BuyProduct> findAllByProductIdIn(List<Long> productIds);

}
