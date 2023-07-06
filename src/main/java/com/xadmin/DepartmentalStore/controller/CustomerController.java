package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Customer;

import com.xadmin.DepartmentalStore.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    @Operation(operationId = "getAllCustomers", summary = "Get all Customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted BackOrder"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/customers")
        public List<Customer> getAllCustomers(){  return customerService.getAllCustomers();  }


    /**
     * Adds a new customer.
     *
     * @param customer the customer to be added
     */
    @Operation(operationId = "addCustomer", summary = "Add Customer Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added Customer Details"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping("/customers")
    public void addCustomer(@RequestBody Customer customer)
    {  customerService.addCustomer(customer);  }


    /**
     * Updates an existing customer with the specified ID.
     *
     * @param id       the ID of the customer to be updated
     * @param customer the updated customer information
     */
    @Operation(operationId = "updateCustomer", summary = "Update Customer Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Customer Details"),
            @ApiResponse(responseCode = "400", description = "Customer with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    { customerService.updateCustomer(id,customer); }


    /**
     * Deletes a customer with the specified ID.
     *
     * @param id the ID of the customer to be deleted
     */
    @Operation(operationId = "deleteCustomer", summary = "Delete Customer Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted Customer with given id"),
            @ApiResponse(responseCode = "400", description = "Customer with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id)
    { customerService.deleteCustomer(id); }

}