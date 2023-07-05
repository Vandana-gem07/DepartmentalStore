package com.xadmin.DepartmentalStore.service;
import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomerService {

    @Value("${email.validation.check}")
    private String emailValidationCheck;

    @Value("${contact.number.check}")
    private String contactValidationCheck;

    @Autowired
    public CustomerRepository customerRepo;
    @Autowired
    private ProductService productService;



    public List<Customer> getAllCustomers()
    {
        return customerRepo.findAll();
    }




    public void isValidEmail(String email) {
        if(!email.matches(emailValidationCheck))
        {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public void isValidNum(String num) {
        if(!num.matches(contactValidationCheck))
        {
            throw new IllegalArgumentException("Invalid Contact Number");
        }
    }

    public void addCustomer(Customer customer) {

          isValidEmail(customer.getEmail());
          isValidNum(customer.getContactNumber());
            customerRepo.save(customer);

    }
    public void updateCustomer(Long id, Customer updatedCustomer) {
        // Retrieve the existing customer from the database
        Optional<Customer> optionalCustomer = customerRepo.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Update the fields of the existing customer with the new values
            existingCustomer.setFullName(updatedCustomer.getFullName());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setContactNumber(updatedCustomer.getContactNumber());

            // Save the updated customer
            isValidEmail(updatedCustomer.getEmail());
            customerRepo.save(existingCustomer);
        } else {
            // Handle the case when the customer with the given ID is not found
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    public void deleteCustomer(Long id) { customerRepo.deleteById(id); }
}
