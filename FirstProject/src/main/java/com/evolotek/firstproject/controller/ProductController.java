package com.evolotek.firstproject.controller;

import com.evolotek.firstproject.model.Product;
import com.evolotek.firstproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // You can limit origins in production
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("GET /api/products/{} called", id);
        return productService.getProductById(id)
                .map(product -> {
                    log.info("Product found with id: {}", id);
                    return ResponseEntity.ok(product);
                })
                .orElseGet(() -> {
                    log.warn("Product not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        log.info("POST /api/products called with data: {}", product);
        Product created = productService.createProduct(product);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        log.info("PUT /api/products/{} called with data: {}", id, updatedProduct);
        return productService.updateProduct(id, updatedProduct)
                .map(product -> {
                    log.info("Product updated with id: {}", id);
                    return ResponseEntity.ok(product);
                })
                .orElseGet(() -> {
                    log.warn("Product not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{} called", id);
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            log.info("Product deleted with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Product not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
