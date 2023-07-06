package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.*;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import com.xadmin.DepartmentalStore.repository.OrderRepository;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepo;
    @Mock
    private ProductRepository productRepo;

    @Mock
    private CustomerRepository custRepo;

    @Mock
    private BackOrderService backServe;


    @InjectMocks
    private OrderService orderService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Create a list of sample orders
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Order());
        expectedOrders.add(new Order());



        // Mock the orderRepo's findAll method to return the sample orders
        when(orderRepo.findAll()).thenReturn(expectedOrders);

        // Call the getAllOrders method of the orderService
        List<Order> actualOrders = orderService.getAllOrders();

        // Verify that the returned orders match the expected orders
        assertEquals(expectedOrders.size(), actualOrders.size());
        assertEquals(expectedOrders, actualOrders);
    }


    @Test
    public void testUpdateDetails() {
        // Create a sample order
        Order order = new Order(new Product(1L, "Chips",20), new Customer(1L, "John Doe","123 Main St","1234567890"), null, 0, null, null);

// Create a sample customer
        Customer updatedCustomer = new Customer(1L, "Vandana Arora","f-block","3459122");
// Create a sample product
        Product updatedProduct = new Product(1L, "Toffee",10);


        // Mock the custRepo to return the sample customer
        when(custRepo.findById(order.getCustomer().getCustomerId())).thenReturn(Optional.of(updatedCustomer));

        // Mock the productRepo to return the sample product
        when(productRepo.findById(order.getProduct().getProductId())).thenReturn(Optional.of(updatedProduct));

        // Call the updateDetails method of the orderService
        orderService.updateDetails(order);

        // Verify the customerRepository findById method was called once with the specified customer ID
        verify(custRepo, times(1)).findById(order.getCustomer().getCustomerId());

        // Verify the productRepository findById method was called once with the specified product ID
        verify(productRepo, times(1)).findById(order.getProduct().getProductId());

        // Verify the order's customer and product details are updated correctly
        assertEquals(updatedCustomer, order.getCustomer());
        assertEquals(updatedProduct, order.getProduct());
    }


    @Test
    public void testUpdateDiscountedPrice() {
        // Create a sample order
        Order order = new Order();
        Product product = new Product();
        product.setPrice(10.0);
        order.setProduct(product);
        order.setQuantity(5);
        order.setDiscount(20.0);

        // Call the updateDiscountedPrice method of the orderService
        orderService.updateDiscountedPrice(order);

        // Verify that the order's discount price is calculated correctly
        double expectedDiscountedPrice = 10.0 * 5 - (10.0 * 5 * 20 / 100);
        assertEquals(expectedDiscountedPrice, order.getDiscountPrice());

        // Verify that the orderRepo save method was called once with the order
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    public void testWithEnoughStock() {
        // Arrange
        Product product = new Product(1L, "Product 1", 10.0);
        product.setCount(10);
//        product.setAvailability(true);

        Order order = new Order(
                product,
                null, // Assuming customer is not required in this scenario
                new Date(), // Assuming orderTimestamp should be the current date
                5,
                null,
                null
        );



        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act
        orderService.isAvailable(order);

        // Assert
        verify(orderRepo).save(order);
        verify(productRepo).save(product);
        assertEquals(5, product.getCount());
    }

    @Test
    public void testWithInsufficientStock() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        product.setProductId(1L);
        product.setCount(5);
//        product.setAvailability(true);
        order.setProduct(product);
        order.setQuantity(10);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> orderService.isAvailable(order));
        verify(backServe).createBackOrder(any(BackOrder.class));
    }



//Tests for Add Order

//    @Test
//    public void testAddOrder() {
//        // Create a sample order
//        Order order = new Order(
//                new Product(1L, "Product 1", 10.0),
//                new Customer(1L, "John Doe", "123 Main St", "1234567890"),
//                new Date(),
//                2,
//                null,
//                null
//        );
//
//
//        // Set a default value for the discount if it is null
//        if (order.getDiscount() == null) {
//            order.setDiscount(0.0);
//        }
//
//        // Mock the custRepo to return the sample customer
//        when(custRepo.findById(order.getCustomer().getCustomerId())).thenReturn(Optional.of(order.getCustomer()));
//
//        // Mock the productRepo to return the sample product
//        when(productRepo.findById(order.getProduct().getProductId())).thenReturn(Optional.of(order.getProduct()));
//
//        // Call the addOrder method of the orderService
//        orderService.addOrder(order);
//
//        // Verify the updateDiscountedPrice method was called once with the order (if discount is present)
//        if (order.getDiscount() != null) {
//            verify(orderService, times(1)).updateDiscountedPrice(order);
//        }
//    }



    @Test
    public void testUpdateOrder() {
        // Create a sample order
        Order order = new Order();
        Long orderId = 1L;

        // Mock the orderRepo to return the sample order
        when(orderRepo.findById(orderId)).thenReturn(Optional.of(order));

        // Call the updateOrder method of the orderService
        orderService.updateOrder(orderId, order);

        // Verify the orderRepository save method was called once with the specified order
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    public void testDeleteOrder() {
        // Create a sample order
        Order order = new Order();
        Long orderId = 1L;

        // Call the deleteOrder method of the orderService
        orderService.deleteOrder(orderId);

        // Verify the orderRepository deleteById method was called once with the specified order ID
        verify(orderRepo, times(1)).deleteById(orderId);
    }


}