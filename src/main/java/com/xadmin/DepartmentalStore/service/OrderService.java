package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.bean.Order;
import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import com.xadmin.DepartmentalStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepo;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;

    HashMap<Long, List<Order>> mp = new HashMap<>();

    public List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        orderRepo.findAll();
        return orders;
    }
    public Boolean addOrder(Order order) {

        Customer customer = order.getCustomer();

        List<Order> l1 = List.of(order);
        customer.setOrders(l1);
        customerRepository.save(customer);

        List<Order> orders = order.getCustomer().getOrders();

        Order orderObj = orders.get(orders.size()-1);
        int q1 = orderObj.getQuantity();   //customer wants
        Long orderId = orderObj.getOrderId();
        long productId = orderObj.getProductId();

        Optional<Product> prod = productService.getProductById(productId);
        Boolean avail = prod.get().isAvailability();
        int q2 = prod.get().getCount();  //that we have in our inventory

        if(avail && q2<q1)
        {
            List<Order> check =  mp.get(productId);

            if (check==null) {
                List<Order> backorderList = new ArrayList<>();
                backorderList.add(order);
                mp.put(productId, backorderList);
            } else {
                mp.get(productId).add(order);
            }

            return true;
        }

        else
        {
            orderRepo.save(order);
            return false;
        }
    }

    public void updateOrder(Long id,Order order) { orderRepo.save(order); }

    public void deleteOrder(Long id) { orderRepo.deleteById(id); }

    public HashMap<Long, List<Order>> BackOrders(){
        return mp;
    }
}
