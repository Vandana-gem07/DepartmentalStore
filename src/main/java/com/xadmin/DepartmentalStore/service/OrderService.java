package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.bean.Order;
import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.BackOrderRepository;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import com.xadmin.DepartmentalStore.repository.OrderRepository;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepo;
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private BackOrderRepository backRepo;


    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    BackOrderService backServe;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo, CustomerRepository custRepo, BackOrderService backServe) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.custRepo = custRepo;
        this.backServe = backServe;
    }

//    public OrderService(ProductRepository productRepo){
//        this.productRepo = productRepo;
//    }

//    HashMap<Long, List<Order>> mp = new HashMap<>();

    public List<Order> getAllOrders() {

        return orderRepo.findAll();
    }

    public void updateDetails(Order order) {
        Customer cust = custRepo.findById(order.getCustomer().getCustomerId()).orElse(null);
        Product pro = productRepo.findById(order.getProduct().getProductId()).orElse(null);

        order.setCustomer(cust);
        order.setProduct(pro);
    }

    public void updateDiscountedPrice(Order order)
    {

        if (order.getDiscount() != null) {

            Product product = order.getProduct();
            double totalPrice = product.getPrice() * order.getQuantity();
            double discountedPrice = totalPrice - (totalPrice * (order.getDiscount()) / 100.0);
            order.setDiscountPrice(discountedPrice);

            orderRepo.save(order);

        }

    }

    public void isAvailable(Order order) {

        Product product = order.getProduct();

        Optional<Product> product1 =  productRepo.findById(product.getProductId());


        if (product1.get().getCount() >= order.getQuantity())
        {
            orderRepo.save(order);
            product.setCount(product.getCount() - order.getQuantity());
            productRepo.save(product);

        } else {
            BackOrder backOrder = new BackOrder();
            backOrder.setOrder(order);
            backServe.createBackOrder(backOrder);
            throw new IllegalStateException("Order placed successfully but out of stock. We will notify you once it is in stock");
        }
    }

    public void addOrder(Order order) {
//        orderRepo.save(order);
//  update other fields when you order something as other fields might go null due to not entering entire details in JSON data
        updateDetails(order);
        updateDiscountedPrice(order);
        isAvailable(order);  //  it checks the count and availability of the order
//        if(order.getDiscount() != null)
    }
    public void updateOrder(Long id, Order order) {

            orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
