package com.evolotek.firstproject.service;

import com.evolotek.firstproject.model.Product;
import com.evolotek.firstproject.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    public void testGetAllProducts() {
        Product p1 = new Product("Pen", "Blue ink pen", 20.0);
        Product p2 = new Product("Notebook", "100 pages", 50.0);

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product("Pencil", "HB Pencil", 10.0);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Pencil", result.get().getName());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product("Eraser", "Soft", 5.0);

        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.createProduct(product);

        assertEquals("Eraser", saved.getName());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("Scale", "30cm", 15.0);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).delete(product);
    }
}
