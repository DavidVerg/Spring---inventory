package com.david.inventory.repository;

import com.david.inventory.domain.Product;

import java.util.List;

public interface ProductsRepository {

    List<Product> findAll();

    Product findById(String id);

    List<Product> findByCategory(String category);

    void create(Product product);

    void update(String id, Product product);

    void delete(String id);
}
