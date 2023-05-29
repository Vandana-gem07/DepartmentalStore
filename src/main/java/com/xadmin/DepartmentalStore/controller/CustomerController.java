package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Customer;

import com.xadmin.DepartmentalStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){  return customerService.getAllCustomers();  }

    @PostMapping("/customers")
    public void addCustomer(@RequestBody Customer customer)
    {  customerService.addCustomer(customer);  }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    { customerService.updateCustomer(id,customer); }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id)
    { customerService.deleteCustomer(id); }

}