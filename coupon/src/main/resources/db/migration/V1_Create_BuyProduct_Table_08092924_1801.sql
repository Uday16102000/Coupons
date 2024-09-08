CREATE TABLE BUY_PRODUCT (
    BUYPRODUCT_ID BIGINT NOT NULL AUTO_INCREMENT,
    PRODUCT_ID BIGINT NOT NULL,
    QUANTITY INT NOT NULL,
    COUPON_ID BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT FK_BUY_PRODUCT_COUPON FOREIGN KEY (COUPON_ID) REFERENCES COUPON(COUPON_ID),
    CONSTRAINT FK_BUY_PRODUCT_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
) ENGINE=InnoDB;
