package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.helper.MyExcelHelper;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    public void save(MultipartFile file) {
        try {
            List<Product> products = MyExcelHelper.convertExcel(file.getInputStream());
            this.productRepo.saveAll(products);
        }

        catch (IOException e) {
            e.printStackTrace();
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
