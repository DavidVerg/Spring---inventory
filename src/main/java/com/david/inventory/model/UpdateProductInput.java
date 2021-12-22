package com.david.inventory.model;

public class UpdateProductInput {

    private String name;
    private Integer stock;
    private String category;
    private String description;

    public UpdateProductInput() {
    }

    public UpdateProductInput(String name, Integer stock, String category, String description) {
        this.name = name;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
