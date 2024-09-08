package com.monk.coupon.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.monk.coupon.entity.BuyProduct;
import com.monk.coupon.entity.Coupon;
import com.monk.coupon.entity.GetProdcut;
import com.monk.coupon.entity.Product;
import com.monk.coupon.enums.CouponTypeConstant;
import com.monk.coupon.exception.MonkException;
import com.monk.coupon.messages.MonkErrorMessages;
import com.monk.coupon.model.ApplicableCouponDetails;
import com.monk.coupon.model.ApplicableCouponRequest;
import com.monk.coupon.model.ApplicableCouponResponse;
import com.monk.coupon.model.BuyProductDetails;
import com.monk.coupon.model.CouponDetails;
import com.monk.coupon.model.CouponRequest;
import com.monk.coupon.model.GetProductDetails;
import com.monk.coupon.repository.BuyProductRepository;
import com.monk.coupon.repository.CouponRepository;
import com.monk.coupon.repository.GetProductRepository;
import com.monk.coupon.repository.ProductRepository;
import com.monk.coupon.service.CouponService;

import jakarta.validation.Valid;

@Service
public class CoupnServiceImpl implements CouponService {

	private static final Logger log = LoggerFactory.getLogger(CoupnServiceImpl.class);

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private GetProductRepository getProductRepository;

	@Autowired
	private BuyProductRepository buyProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void createNewCoupon(CouponRequest couponRequest) throws MonkException {

		log.info("service started for creating new coupon");

		if (CouponTypeConstant.CARTWISE.equalsIgnoreCase(couponRequest.getCouponType())) {
			saveCouponForCart(couponRequest);
		} else if (CouponTypeConstant.PRODUCTWISE.equals(couponRequest.getCouponType())) {
			saveCouponForProduct(couponRequest);
		} else if (CouponTypeConstant.BXGY.equals(couponRequest.getCouponType())) {
			saveCouponForBxGy(couponRequest);
		} else {
			throw new MonkException(MonkErrorMessages.INAVLID_COUPON_TYPE, HttpStatus.BAD_REQUEST);
		}

		log.info("service ended for creating new coupon");

	}

	/**
	 * @param couponRequest
	 * @throws MonkException
	 */
	private void saveCouponForBxGy(CouponRequest couponRequest) throws MonkException {

		List<Long> buyProductIds = new ArrayList();

		couponRequest.getCouponDetails().getBuyProduct().forEach(bx -> {
			buyProductIds.add(bx.getProductId());
		});

		List<Product> buyProdList = productRepository.findAllByProductIdInAndIsDeletedFalse(buyProductIds);

		if (buyProdList.size() != buyProductIds.size()) {

			throw new MonkException(MonkErrorMessages.BUY_PRODUCT_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);

		}

		Double[] totalBuyPrice = { 0.0 };

		buyProdList.forEach(buyProduct -> {
			couponRequest.getCouponDetails().getBuyProduct().forEach(by -> {
				if (by.getProductId() == buyProduct.getProductId()) {
					if (buyProduct.getQuantity() < by.getQuantity()) {
						throw new MonkException(
								MonkErrorMessages.BUY_PRODUCT_QUANTITY_INSUFFICIENT + " " + by.getProductId(),
								HttpStatus.BAD_REQUEST);

					}
				}
			});
			totalBuyPrice[0] = totalBuyPrice[0] + buyProduct.getPrice();
		});

		List<Long> getProductIds = new ArrayList();

		couponRequest.getCouponDetails().getGetProducts().forEach(gy -> {
			getProductIds.add(gy.getProductId());
		});
		List<Product> getProdList = productRepository.findAllByProductIdInAndIsDeletedFalse(getProductIds);

		if (getProdList.size() != getProductIds.size()) {

			throw new MonkException(MonkErrorMessages.GET_PRODUCT_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);

		}
		Double[] totalGetPrice = { 0.0 };
		getProdList.forEach(getProduct -> {
			couponRequest.getCouponDetails().getBuyProduct().forEach(gy -> {
				if (gy.getProductId() == getProduct.getProductId()) {
					if (getProduct.getQuantity() < gy.getQuantity()) {
						throw new MonkException(
								MonkErrorMessages.GET_PRODUCT_QUANTITY_INSUFFICIENT + " " + gy.getProductId(),
								HttpStatus.BAD_REQUEST);

					}
				}
			});

			totalGetPrice[0] += getProduct.getPrice();
		});

		Double discount = totalBuyPrice[0] + totalGetPrice[0] - totalGetPrice[0];

		Coupon coupon = new Coupon();
		coupon.setCouponType(couponRequest.getCouponType());
		coupon.setDiscount(discount);
		coupon.setCouponCondition(couponRequest.getCouponDetails().getCouponCondition());
		coupon.setCouponExpirationTime(couponRequest.getCouponDetails().getExpireTime());
		coupon.setRepetitionLimit(couponRequest.getCouponDetails().getRepetitionLimit());
		coupon.setCouponName(couponRequest.getCouponName());
		Coupon savedCoupon = couponRepository.save(coupon);
		List<BuyProduct> buyProductsList = new ArrayList<BuyProduct>();
		couponRequest.getCouponDetails().getBuyProduct().forEach(by -> {
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setCoupon(savedCoupon);
			buyProduct.setProductId(by.getProductId());
			buyProduct.setQuantity(by.getQuantity());
			buyProductsList.add(buyProduct);
		});

		buyProductRepository.saveAll(buyProductsList);

		List<GetProdcut> getProductsList = new ArrayList<>();
		couponRequest.getCouponDetails().getGetProducts().forEach(gy -> {
			GetProdcut getProduct = new GetProdcut();
			getProduct.setCoupon(savedCoupon);
			getProduct.setProductId(gy.getProductId());
			getProduct.setQuantity(gy.getQuantity());
			getProductsList.add(getProduct);
		});
		getProductRepository.saveAll(getProductsList);

	}

	/**
	 * @param couponRequest
	 * @throws MonkException
	 */
	private void saveCouponForProduct(CouponRequest couponRequest) throws MonkException {

		Optional<Product> isProductPresnt = productRepository
				.findByProductIdAndIsDeletedFalse(couponRequest.getCouponDetails().getProductId());

		if (isProductPresnt.isEmpty()) {
			throw new MonkException(
					MonkErrorMessages.NO_PRODUCT_PRESENT + " " + couponRequest.getCouponDetails().getProductId(),
					HttpStatus.BAD_REQUEST);
		}

		Coupon coupon = new Coupon();
		coupon.setCouponType(couponRequest.getCouponType());
		coupon.setDiscount(couponRequest.getCouponDetails().getDiscount());
		coupon.setCouponExpirationTime(couponRequest.getCouponDetails().getExpireTime());
		coupon.setCouponName(couponRequest.getCouponName());

		Coupon savedCoupon = couponRepository.save(coupon);

		isProductPresnt.get().getCoupons().add(savedCoupon);
		productRepository.save(isProductPresnt.get());

	}

	/**
	 * @param couponRequest
	 */
	private void saveCouponForCart(CouponRequest couponRequest) {

		Coupon coupon = new Coupon();
		coupon.setCouponType(couponRequest.getCouponType());
		coupon.setDiscount(couponRequest.getCouponDetails().getDiscount());
		coupon.setThreshold(couponRequest.getCouponDetails().getThreshold());
		coupon.setCouponCondition(couponRequest.getCouponDetails().getCouponCondition());
		coupon.setCouponExpirationTime(couponRequest.getCouponDetails().getExpireTime());
		coupon.setCouponName(couponRequest.getCouponName());

		couponRepository.save(coupon);

	}

	@Override
	public List<CouponRequest> getAllCoupon() {
		log.info("service started for  fetching all coupon");

		List<Coupon> couponList = couponRepository.findAllByIsDeletedFalse();

		List<BuyProduct> buyProductList = buyProductRepository.findAllByCouponIn(couponList);
		List<Product> productList = productRepository.findAllByIsDeletedFalse();

		List<GetProdcut> getProdcutList = getProductRepository.findAllByCouponIn(couponList);
		List<CouponRequest> couponListResponse = new ArrayList<CouponRequest>();
		couponList.forEach(coupon -> {
			CouponRequest couponRequest = new CouponRequest();
			CouponDetails couponDetails = new CouponDetails();
			couponRequest.setCouponType(coupon.getCouponType());
			couponRequest.setCouponName(coupon.getCouponName());
			couponDetails.setDiscount(coupon.getDiscount());
			couponDetails.setExpireTime(coupon.getCouponExpirationTime());

			if (coupon.getCouponType().equalsIgnoreCase(CouponTypeConstant.BXGY)) {
				couponDetails.setRepetitionLimit(coupon.getRepetitionLimit());
				List<BuyProductDetails> buyProductDetailList = new ArrayList<BuyProductDetails>();
				buyProductList.forEach(by -> {
					if (by.getCoupon().getCouponId() == coupon.getCouponId()) {
						BuyProductDetails buyProductDetails = new BuyProductDetails();

						buyProductDetails.setProductId(by.getProductId());
						buyProductDetails.setQuantity(by.getQuantity());
						buyProductDetailList.add(buyProductDetails);
					}
				});
				couponDetails.setBuyProduct(buyProductDetailList);

				List<GetProductDetails> getProductDetailList = new ArrayList<GetProductDetails>();
				getProdcutList.forEach(gy -> {
					if (gy.getCoupon().getCouponId() == coupon.getCouponId()) {
						GetProductDetails getProductDetails = new GetProductDetails();

						getProductDetails.setProductId(gy.getProductId());
						getProductDetails.setQuantity(gy.getQuantity());
						getProductDetailList.add(getProductDetails);
					}
				});
				couponDetails.setGetProducts(getProductDetailList);
				couponRequest.setCouponDetails(couponDetails);

			} else if (coupon.getCouponType().equalsIgnoreCase(CouponTypeConstant.CARTWISE)) {
				couponDetails.setThreshold(coupon.getThreshold());
				couponRequest.setCouponDetails(couponDetails);

			} else if (coupon.getCouponType().equalsIgnoreCase(CouponTypeConstant.PRODUCTWISE)) {
				productList.forEach(prod -> {
					if (prod.getCoupons().contains(coupon)) {
						couponDetails.setProductId(prod.getProductId());
					}
				});
				couponRequest.setCouponDetails(couponDetails);

			}
			couponListResponse.add(couponRequest);

		});
		log.info("service ended for  fetching all coupon");

		return couponListResponse;
	}

	@Override
	public CouponRequest fetchCoupon(Long couponId) {
		Optional<Coupon> coupon = couponRepository.findByCouponId(couponId);
		if (coupon.isEmpty()) {
			throw new MonkException(MonkErrorMessages.COUPON_NOT_PRESENT + " " + couponId, HttpStatus.BAD_REQUEST);
		}

		List<BuyProduct> buyProductList = buyProductRepository.findAllByCoupon(coupon.get());
		List<Product> productList = productRepository.findAllByIsDeletedFalse();

		List<GetProdcut> getProdcutList = getProductRepository.findAllByCoupon(coupon);

		CouponRequest couponRequest = new CouponRequest();
		CouponDetails couponDetails = new CouponDetails();
		couponRequest.setCouponType(coupon.get().getCouponType());
		couponRequest.setCouponName(coupon.get().getCouponName());
		couponDetails.setDiscount(coupon.get().getDiscount());
		couponDetails.setExpireTime(coupon.get().getCouponExpirationTime());

		if (coupon.get().getCouponType().equalsIgnoreCase(CouponTypeConstant.BXGY)) {
			couponDetails.setRepetitionLimit(coupon.get().getRepetitionLimit());
			List<BuyProductDetails> buyProductDetailList = new ArrayList<BuyProductDetails>();
			buyProductList.forEach(by -> {
				if (by.getCoupon().getCouponId() == coupon.get().getCouponId()) {
					BuyProductDetails buyProductDetails = new BuyProductDetails();

					buyProductDetails.setProductId(by.getProductId());
					buyProductDetails.setQuantity(by.getQuantity());
					buyProductDetailList.add(buyProductDetails);
				}
			});
			couponDetails.setBuyProduct(buyProductDetailList);

			List<GetProductDetails> getProductDetailList = new ArrayList<GetProductDetails>();
			getProdcutList.forEach(gy -> {
				if (gy.getCoupon().getCouponId() == coupon.get().getCouponId()) {
					GetProductDetails getProductDetails = new GetProductDetails();

					getProductDetails.setProductId(gy.getProductId());
					getProductDetails.setQuantity(gy.getQuantity());
					getProductDetailList.add(getProductDetails);
				}
			});
			couponDetails.setGetProducts(getProductDetailList);
			couponRequest.setCouponDetails(couponDetails);

		} else if (coupon.get().getCouponType().equalsIgnoreCase(CouponTypeConstant.CARTWISE)) {
			couponDetails.setThreshold(coupon.get().getThreshold());
			couponRequest.setCouponDetails(couponDetails);

		} else if (coupon.get().getCouponType().equalsIgnoreCase(CouponTypeConstant.PRODUCTWISE)) {
			productList.forEach(prod -> {
				if (prod.getCoupons().contains(coupon.get())) {
					couponDetails.setProductId(prod.getProductId());
				}
			});
			couponRequest.setCouponDetails(couponDetails);

		}
		return couponRequest;
	}

	@Override
	public ApplicableCouponResponse getAllApplicableCoupons(ApplicableCouponRequest applicableCouponRequest) {

		List<Long> productIds = new ArrayList<Long>();
		applicableCouponRequest.getCart().getItems().forEach(item -> {

			productIds.add(item.getProduct_id());

		});
		
		List<Product> productList= productRepository.findAllByProductIdInAndIsDeletedFalse(productIds);
		if(productIds.size() != productIds.size())
		{
			throw new MonkException(MonkErrorMessages.FEW_PRODUCT_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);
		}
		
		
		

		List<Coupon> couponList = couponRepository.findAllByIsDeletedFalse();


		List<BuyProduct> buyProductList = buyProductRepository.findAllByProductIdIn(productIds);

		List<GetProdcut> getProdcutList = getProductRepository.findAllByProductIdIn(productIds);
		
		List<ApplicableCouponResponse> applicableCouponResponse= new ArrayList<ApplicableCouponResponse>();
		productList.forEach(product->{
			ApplicableCouponDetails applicableCouponDetails = new ApplicableCouponDetails();
            			
		});

		return null;
	}

}
