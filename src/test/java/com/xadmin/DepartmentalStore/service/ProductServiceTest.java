package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 10.0));
        products.add(new Product(2L, "Product 2", 20.0));

        // Stub the findAll method of productRepo to return the list of products
        when(productRepo.findAll()).thenReturn(products);

        // Call the getAllProducts method of the productService
        List<Product> result = productService.getAllProducts();

        // Verify that the result matches the expected list of products
        Assertions.assertEquals(products, result);
    }

    @Test
    void testAddProduct() {
        // Create a product
        Product product = new Product(1L, "Product 1", 10.0);

        // Call the addProduct method of the productService
        productService.addProduct(product);

        // Verify that the save method of productRepo is called once with the given product
        verify(productRepo, times(1)).save(product);
    }

    @Test
    void testAddProduct_Exception() {
        // Create a product
        Product product = new Product(1L, "Product 1", 10.0);

        // Stub the save method of productRepo to throw an exception
        doThrow(new RuntimeException()).when(productRepo).save(product);

        // Verify that a RuntimeException is thrown when calling addProduct
        assertThrows(RuntimeException.class, () -> productService.addProduct(product));

        // Verify that the save method of productRepo is called once with the given product
        verify(productRepo, times(1)).save(product);
    }


    @Test
    void testGetProductById() {
        // Create a product
        Product product = new Product(1L, "Product 1", 10.0);

        // Stub the findById method of productRepo to return the product
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Call the getProductById method of the productService
        Optional<Product> result = productService.getProductById(1L);

        // Verify that the result matches the expected product
        Assertions.assertEquals(Optional.of(product), result);
    }

    @Test
    void testUpdateProduct() {
        // Create a product
        Product product = new Product(1L, "Product 1", 10.0);

        // Call the updateProduct method of the productService
        productService.updateProduct(1L, product);

        // Verify that the save method of productRepo is called once with the given product
        verify(productRepo, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        // Call the deleteProduct method of the productService
        productService.deleteProduct(1L);

        // Verify that the deleteById method of productRepo is called once with the given ID
        verify(productRepo, times(1)).deleteById(1L);
    }
}
