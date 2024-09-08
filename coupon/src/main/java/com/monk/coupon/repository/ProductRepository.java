package com.monk.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monk.coupon.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	Optional<Product> findByProductIdAndIsDeletedFalse(Long productId);

	boolean existsByProductName(String productName);

	List<Product> findAllByProductIdInAndIsDeletedFalse(List<Long> productIds);

	List<Product> findAllByIsDeletedFalse();

}
