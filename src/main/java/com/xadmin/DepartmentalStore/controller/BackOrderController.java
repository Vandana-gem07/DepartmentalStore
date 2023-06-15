package com.xadmin.DepartmentalStore.controller;
import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.service.BackOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BackOrderController {

    @Autowired
    private BackOrderService backOrderService;

    /**
     * Creates a new back order.
     *
     * @param backOrder the BackOrder object containing the details of the back order
     * @return a ResponseEntity with a success message if the back order is created successfully
     */

    @Operation(operationId = "addBackOrder", summary = "Add backorder details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added BackOrder"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})


    @PostMapping("/backOrders")
    public ResponseEntity<String> createBackOrder(@RequestBody BackOrder backOrder) {
        backOrderService.createBackOrder(backOrder);
        return ResponseEntity.ok("Backorder created successfully.");
    }

    /**
     * Retrieves all back orders.
     *
     * @return a list of BackOrder objects representing all back orders
     */
    @Operation(operationId = "getAllBackOrder", summary = "Get all BackOrder details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved BackOrder"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/backOrders")
    public List<BackOrder> getAllBackOrders() {
        return backOrderService.getAllBackOrders();
    }



    /**
     * Retrieves a specific back order by its ID.
     *
     * @param backOrderId the ID of the back order to retrieve
     * @return a ResponseEntity containing the BackOrder object if found, or an empty response if not found
     */
    @Operation(operationId = "getBackOrder", summary = "Get BackOrder details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added BackOrder"),
            @ApiResponse(responseCode = "400", description = "BackOrder with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/backOrders/{backOrderId}")
    public ResponseEntity<BackOrder> getBackOrderById(@PathVariable Long backOrderId) {
        BackOrder backOrder = backOrderService.getBackOrderById(backOrderId);
        return ResponseEntity.ok(backOrder);
    }


    /**
     * Deletes a specific back order by its ID.
     *
     * @param backOrderId the ID of the back order to delete
     * @return a ResponseEntity indicating the deletion status
     */
    @Operation(operationId = "deleteBackOrder", summary = "Delete BackOrder with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted BackOrder"),
            @ApiResponse(responseCode = "400", description = "BackOrder with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/backOrders/{backOrderId}")
    public ResponseEntity<String> deleteBackOrder(@PathVariable Long backOrderId) {
        backOrderService.deleteBackOrder(backOrderId);
        return ResponseEntity.ok("Backorder deleted successfully.");
    }
}