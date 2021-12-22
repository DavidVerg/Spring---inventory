package com.david.inventory.controller;

import com.david.inventory.domain.*;
import com.david.inventory.model.CreateProductInput;
import com.david.inventory.model.UpdateProductInput;
import com.david.inventory.repository.ProductsRepository;
import com.david.inventory.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServices services;

    public ProductController(ProductServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProdcuts() {
        List<Product> products = services.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String unsafeProductId) {
        final ProductId productId = ProductId.fromString(unsafeProductId);
        Product product = services.findById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") ProductCategory category) {
        List<Product> products = services.findByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductInput input) {

        ProductId productId = ProductId.random();
        ProductName productName = new ProductName(input.getName());
        ProductStock productStock = new ProductStock(input.getStock());
        ProductCategory productCategory = new ProductCategory(input.getCategory());
        ProductDescription productDescription = new ProductDescription(input.getDescription());

        Product product = new Product(productId, productName, productStock, productCategory, productDescription);

        Product createdProduct = services.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String unsafeProductId, @RequestBody UpdateProductInput input) {

        final ProductId productId = ProductId.fromString(unsafeProductId);

        ProductName productName = new ProductName(input.getName());
        ProductStock productStock = new ProductStock(input.getStock());
        ProductCategory productCategory = new ProductCategory(input.getCategory());
        ProductDescription productDescription = new ProductDescription(input.getDescription());

        Product product = new Product(productId, productName, productStock, productCategory, productDescription);

        Product updatedProduct = services.update(productId, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProd(@PathVariable("id") String unsafeProductId) {
        final ProductId productId = ProductId.fromString(unsafeProductId);
        services.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
