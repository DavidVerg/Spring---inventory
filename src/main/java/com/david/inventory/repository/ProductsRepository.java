package com.david.inventory.repository;

import com.david.inventory.domain.Product;
import com.david.inventory.domain.ProductCategory;
import com.david.inventory.domain.ProductId;

import java.util.List;

public interface ProductsRepository {

    List<Product> findAll();

    Product findById(ProductId id);

    List<Product> findByCategory(ProductCategory category);

    void create(Product product);

    void update(ProductId id, Product product);

    void delete(ProductId id);
}
