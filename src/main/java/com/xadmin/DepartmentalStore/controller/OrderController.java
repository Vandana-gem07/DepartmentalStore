package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Order;

import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.service.OrderService;
import com.xadmin.DepartmentalStore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    @Operation(operationId = "getAllOrders", summary = "Get All order Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all orders"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //ResponseEntity<Map<Long , List<Order>>>

    /**
     * Adds a new order.
     *
     * @param order the order to be added
     * @return a ResponseEntity indicating the success of the operation
     */
    @Operation(operationId = "addOrder", summary = "Add order Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Added Order"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping("/orders")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order placed successfully.");
    }

//    code where backorder was stored in hashmaps
//    @PostMapping("/orders")
//    public String addOrder(@RequestBody Order order)
//    {
//        Boolean ans = orderService.addOrder(order);
//
//        if(ans == false)
//            return "Voila! Your order has been Placed";
//        else
//            return "This product is currently unavailable, we'll notify you once it will be available again";
//    }


    /**
     * Updates an existing order.
     *
     * @param id    the ID of the order to be updated
     * @param order the updated order information
     */
    @Operation(operationId = "updateOrder", summary = "Update order details with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated order with given id"),
            @ApiResponse(responseCode = "400", description = "Order with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/orders/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderService.updateOrder(id, order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to be deleted
     */
    @Operation(operationId = "deleteOrder", summary = "Delete order with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted order with given id"),
            @ApiResponse(responseCode = "400", description = "Order with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}



//    @GetMapping("BackOrders")
//    public HashMap<Long, List<Order>> BackOrders(){
//        return orderService.BackOrders();
//    }

