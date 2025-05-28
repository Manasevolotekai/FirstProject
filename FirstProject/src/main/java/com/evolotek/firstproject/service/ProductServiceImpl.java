package com.evolotek.firstproject.service;

import com.evolotek.firstproject.exception.ProductNotFoundException;
import com.evolotek.firstproject.model.Product;
import com.evolotek.firstproject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        log.info("Fetching product with id: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        log.info("Creating new product: {}", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        log.info("Updating product with id: {}", id);
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    log.info("Product updated with id: {}", id);
                    return productRepository.save(product);
                });
    }

    @Override
    public boolean deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product not found with id: {}", id);
            return false;
        }
        productRepository.delete(productOptional.get());
        log.info("Product deleted successfully with id: {}", id);
        return true;
    }
}
