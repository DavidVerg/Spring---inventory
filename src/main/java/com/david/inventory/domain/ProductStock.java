package com.david.inventory.domain;

import java.util.Objects;

public class ProductStock {

    private final Integer value;

    public ProductStock increment() {
        return new ProductStock(value + 1);
    }

    public ProductStock(Integer value) {
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
