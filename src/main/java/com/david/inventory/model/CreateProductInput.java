package com.david.inventory.model;

public class CreateProductInput {

    private String productName;
    private Integer productQuantity;
    private String productCategory;
    private String productDescription;

    public CreateProductInput() {
    }

    public CreateProductInput(String name, Integer stock, String category, String description) {
        this.productName = name;
        this.productQuantity = stock;
        this.productCategory = category;
        this.productDescription = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
