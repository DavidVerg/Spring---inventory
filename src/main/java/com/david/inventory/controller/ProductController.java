package com.david.inventory.controller;

import com.david.inventory.domain.*;
import com.david.inventory.model.CreateProductInput;
import com.david.inventory.repository.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductsRepository repository;

    public ProductController(ProductsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProdcuts() {
        List<Product> products = repository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String productId) {
        final ProductId id = ProductId.fromString(productId);
        Product product = repository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") ProductCategory category) {
        List<Product> products = repository.findByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductInput input) {

        Product product = new Product(
                ProductId.random(),
                new ProductName(input.getName()),
                new ProductStock(input.getStock()),
                new ProductCategory(input.getCategory()),
                new ProductDescription(input.getDescription())
        );
        repository.create(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") ProductId id, @RequestBody Product product) {
        repository.update(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProd(@PathVariable("id") String productId) {
        final ProductId id = ProductId.fromString(productId);
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
