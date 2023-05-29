package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Product;

import com.xadmin.DepartmentalStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
@Autowired
    private ProductService productService;

@GetMapping("/products")
    public List<Product> getAllProducts(){  return productService.getAllProducts();  }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product)
    {  productService.addProduct(product);
    return "Product is created with ID : " + product.getProductId();
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product)
    { productService.updateProduct(id,product); }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id)
    { productService.deleteProduct(id); }

}
