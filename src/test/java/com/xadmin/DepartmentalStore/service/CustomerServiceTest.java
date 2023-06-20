package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository custRepo;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Create sample customers
        List<Customer> customers = new ArrayList<>();
//        Customer customer = new Customer();
        customers.add(new Customer(1L,"John Doe","123 Main St","1234567890"));
        customers.add(new Customer(2L,"Vandana Arora","F-block","1234567890"));


        when(custRepo.findAll()).thenReturn(customers);
        List<Customer> result = customerService.getAllCustomers();
        verify(custRepo, times(1)).findAll();
        assertEquals(customers, result);
    }


    @Test
    public void testAddCustomer() {
        // Create a sample customer
        Customer customer = new Customer(1L,"John Doe","123 Main St","1234567890");

        // Call the addCustomer method of the customerService
        customerService.addCustomer(customer);

        // Verify the customerRepository save method was called once with the sample customer
        verify(custRepo, times(1)).save(customer);
    }

    @Test
    void testAddCustomer_Exception() {
        // Create a customer
        Customer customer = new Customer(1L, "John Doe", "123 Main St", "1234567890");

        // Stub the save method of customerRepo to throw an exception
        doThrow(new RuntimeException()).when(custRepo).save(customer);

        // Verify that a RuntimeException is thrown when calling addCustomer
        assertThrows(RuntimeException.class, () -> customerService.addCustomer(customer));

        // Verify that the save method of customerRepo is called once with the given customer
        verify(custRepo, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomer() {
        // Create a sample customer
        Customer customer = new Customer(1L,"John Doe","123 Main St","1234567890");


        // Call the updateCustomer method of the customerService
        customerService.updateCustomer(1L, customer);

        // Verify the customerRepository save method was called once with the sample customer
        verify(custRepo, times(1)).save(customer);
    }

    @Test
    public void testDeleteCustomer() {
        // Call the deleteCustomer method of the customerService
        customerService.deleteCustomer(1L);

        // Verify the customerRepository deleteById method was called once with the specified customer ID
        verify(custRepo, times(1)).deleteById(1L);
    }
}
