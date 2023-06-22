package com.xadmin.DepartmentalStore.service;
import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Pattern;

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


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static void isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if(!(pattern.matcher(email).matches()))
        {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public void addCustomer(Customer customer) {

          isValidEmail(customer.getEmail());
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
