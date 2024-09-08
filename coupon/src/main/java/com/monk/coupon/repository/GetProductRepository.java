package com.monk.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monk.coupon.entity.Coupon;
import com.monk.coupon.entity.GetProdcut;

public interface GetProductRepository  extends JpaRepository<GetProdcut, Long>{

	List<GetProdcut> findAllByCouponIn(List<Coupon> couponList);

	List<GetProdcut> findAllByCoupon(Optional<Coupon> coupon);

	List<GetProdcut> findAllByProductIdIn(List<Long> productIds);

}
