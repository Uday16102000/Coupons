package com.monk.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monk.coupon.entity.Coupon;

public interface CouponRepository  extends JpaRepository<Coupon, Long>{

	List<Coupon> findAllByIsDeletedFalse();

	Optional<Coupon> findByCouponId(Long couponId);


}
