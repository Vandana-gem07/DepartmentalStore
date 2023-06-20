package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
    public ProductRepository productRepo;
    public List<Product> getAllProducts()
    {
      return productRepo.findAll();
    }

    public void addProduct(Product product) {
        try {
            productRepo.save(product);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the product.");
        }
    }

     public Optional<Product> getProductById(long id)
    {
        Optional<Product> product = productRepo.findById(id);
        return product;
    }

    public void updateProduct(Long id,Product product) { productRepo.save(product); }

    public void deleteProduct(Long id) { productRepo.deleteById(id); }
}
