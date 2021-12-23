package com.david.inventory.domain;

import java.util.Objects;

public class ProductQuantity {

    private final Integer value;

    public ProductQuantity increment() {
        return new ProductQuantity(value + 1);
    }

    public ProductQuantity(Integer value) {
        Objects.requireNonNull(value, "Product stock quantity can not be null");
        if(value < 0 || value > 300) {
            throw new IllegalArgumentException("Product stock quantity can not be less than 0 or greater than 300");
        }
        this.value = value;
    }

    public Integer asInteger() {
        return value;
    }

}
