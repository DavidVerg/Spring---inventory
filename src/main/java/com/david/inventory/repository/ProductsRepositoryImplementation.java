package com.david.inventory.repository;

import com.david.inventory.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductsRepositoryImplementation implements ProductsRepository {

    List<Product> db = new ArrayList<>() {{
        add(new Product("1", "Papa", "verdura", "Papa"));
        add(new Product("2", "Manzana", "fruta", "Manzana"));
        add(new Product("3", "Zanahoria", "verdura", "Zanahoria"));
        add(new Product("4", "Lechuga", "hortaliza", "Lechuga"));
        add(new Product("5", "Pera", "fruta", "Pera"));
    }};

    @Override
    public List<Product> findAll() {
        return db;
    }

    @Override
    public Product findById(String id) {
        return db.stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return db.stream()
                .filter(product -> category.equals(product.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Product product) {
        Product foundProduct = findById(product.getId());
        if (foundProduct != null) {
            throw new IllegalArgumentException("The product identified with the id: " + product.getId() + " already exists.");
        }
        db.add(product);
    }

    @Override
    public void update(String id, Product product) {
        Product foundProduct = findById(id);
        if (foundProduct == null) {
            throw new IllegalArgumentException("The product identified with the id: " + product.getId() + " doesn't exist.");
        }
        db.set(db.indexOf(foundProduct), product);
    }

    @Override
    public void delete(String id) {
        Product foundProduct = findById(id);
        if (foundProduct == null) {
            throw new IllegalArgumentException("The product identified with the id: " + id + " doesn't exist.");
        }
        db.remove(db.indexOf(foundProduct));
    }
}
