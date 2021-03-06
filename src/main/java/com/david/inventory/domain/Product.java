package com.david.inventory.domain;

import java.util.Objects;

public class Product {

    private final ProductId productId;
    private final ProductName productName;
    private final ProductQuantity productQuantity;
    private final ProductCategory productCategory;
    private final ProductDescription productDescription;

    public Product(ProductId productId, ProductName productName, ProductQuantity productQuantity, ProductCategory productCategory, ProductDescription productDescription) {

        Objects.requireNonNull(productId, "Product id can not be bull");
        Objects.requireNonNull(productName, "Product name can not be bull");
        Objects.requireNonNull(productQuantity, "Product stock can not be bull");
        Objects.requireNonNull(productCategory, "Product category can not be bull");
        Objects.requireNonNull(productName, "Product description can not be bull");

        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
    }

    public ProductId getProductId() {
        return productId;
    }

    public ProductName getProductName() {
        return productName;
    }

    public ProductQuantity getProductQuantity() {
        return productQuantity;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }
}


