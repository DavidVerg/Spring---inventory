package com.david.inventory.services;

import com.david.inventory.domain.Product;
import com.david.inventory.domain.ProductCategory;
import com.david.inventory.domain.ProductId;
import com.david.inventory.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    private ProductsRepository repository;

    public ProductServices(ProductsRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(ProductId id) {
        return repository.findById(id);
    }

    public List<Product> findByCategory(ProductCategory category) {
        return repository.findByCategory(category);
    }

    public Product create(Product product) {
        repository.create(product);
        return repository.findById(product.getProductId());
        //return product;
    }

    public Product update(ProductId id, Product product) {
        repository.update(id, product);
        return repository.findById(id);
    }

    public void delete(ProductId id) {
        repository.delete(id);
    }
}
