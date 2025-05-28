package com.evolotek.firstproject.service;

import com.evolotek.firstproject.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product);

    Optional<Product> updateProduct(Long id, Product updatedProduct);

    boolean deleteProduct(Long id);
}
