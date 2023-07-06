package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.bean.Order;
import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.helper.MyExcelHelper;
import com.xadmin.DepartmentalStore.repository.BackOrderRepository;
import com.xadmin.DepartmentalStore.repository.OrderRepository;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  public ProductRepository productRepo;

  @Autowired
  public BackOrderService backServe;

//  @Autowired
//  public OrderRepository orderRepo;


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

    public void updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()) {

            Product existingProduct = optionalProduct.get();
//            int oldCount = existingProduct.getCount();
            int newCount = product.getCount();

            // Update the fields of the existing product with the new values
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductDesc(product.getProductDesc());
            existingProduct.setCount(newCount);

            // Save the updated product
            productRepo.save(existingProduct);


        } else {
            // Handle the case when the product with the given ID is not found
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    public void deleteBackorder(int newCount, Product existingProduct)
    {
        if (existingProduct.getCount() >= newCount) {
            // Retrieve the associated backorders for the product
            List<BackOrder> backOrders = backServe.getAllBackOrders();


            // Remove the associated backorders if the product count is sufficient
            for (BackOrder backOrder : backOrders) {
                if (existingProduct.getCount() >= backOrder.getOrder().getQuantity()) {
                    backServe.deleteBackOrder(backOrder.getBackOrderId());
                    existingProduct.setCount(existingProduct.getCount() - backOrder.getOrder().getQuantity());
                    productRepo.save(existingProduct);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void removeBackOrdersAutomatically() {
        // Retrieve the products with updated counts
        List<Product> updatedProducts = productRepo.findAll();

        for (Product product : updatedProducts) {
            int newCount = product.getCount();
            deleteBackorder(newCount,product);
        }

    }




    public void deleteProduct(Long id) { productRepo.deleteById(id); }
}
