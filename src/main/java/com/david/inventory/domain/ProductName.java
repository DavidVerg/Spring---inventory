package com.david.inventory.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class ProductName {

    private final String value;
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z\\u00C0-\\u017F\\s]{3,}+$");

    public ProductName(String value) {
        Objects.requireNonNull(value, "Product name can not be null");
        this.value = value;

        String trimmedValue = value.trim();

        if(trimmedValue.length()  == 0) {
            throw new IllegalArgumentException("Product name can not be empty");
        }

        boolean isValid = pattern.matcher(trimmedValue).matches();


        if(!isValid) {
            throw new IllegalArgumentException("Invalid product name");
        }

    }

    @Override
    public String toString() {
        return value;
    }
}
