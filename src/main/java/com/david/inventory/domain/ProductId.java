package com.david.inventory.domain;

import java.util.Objects;
import java.util.UUID;

public class ProductId {

    private final UUID value;

    public ProductId(UUID value) {
        Objects.requireNonNull(value, "Product id can not be null");
        this.value = value;
    }

    public static ProductId fromString(String unsafeValue) {
        return new ProductId(UUID.fromString(unsafeValue));
    }

    public static ProductId random() {
        return new ProductId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
