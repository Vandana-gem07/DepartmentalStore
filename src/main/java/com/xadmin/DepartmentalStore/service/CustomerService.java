package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.bean.Order;
import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepo;
    @Autowired
    private ProductService productService;
    public List<Customer> getAllCustomers()
    {
        return customerRepo.findAll();
    }



    public void addCustomer(Customer customer)
    {

          customerRepo.save(customer);

    }








    public void updateCustomer(Long id,Customer customer) { customerRepo.save(customer); }

    public void deleteCustomer(Long id) { customerRepo.deleteById(id); }
}
