package com.david.inventory.model;

import com.david.inventory.domain.Product;

public class UpdateProductOutput {

    private Product product;

    public UpdateProductOutput() {
    }

    public UpdateProductOutput(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
