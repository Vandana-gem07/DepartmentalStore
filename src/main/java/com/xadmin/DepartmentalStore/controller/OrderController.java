package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Order;

import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.service.OrderService;
import com.xadmin.DepartmentalStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public List<Order> getAllOrders(){  return orderService.getAllOrders();  }

    //ResponseEntity<Map<Long , List<Order>>>
    @PostMapping("/orders")
    public String addOrder(@RequestBody Order order)
    {
        Boolean ans = orderService.addOrder(order);

        if(ans == false)
            return "Voila! Your order has been Placed";
        else
            return "This product is currently unavailable, we'll notify you once it will be available again";
    }

    @PutMapping("/orders/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order)
    { orderService.updateOrder(id,order); }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id)
    { orderService.deleteOrder(id); }

    @GetMapping("BackOrders")
    public HashMap<Long, List<Order>> BackOrders(){
        return orderService.BackOrders();
    }

}