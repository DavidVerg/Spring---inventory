package com.david.inventory.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class ProductDescription {

    private final String value;

    public ProductDescription(String value) {

        Objects.requireNonNull(value, "Product description can not be null");

        String trimmedValue = value.trim();

        if(trimmedValue.length()  == 0) {
            throw new IllegalArgumentException("Product description can not be empty");
        }

        this.value = value;
    }



}
