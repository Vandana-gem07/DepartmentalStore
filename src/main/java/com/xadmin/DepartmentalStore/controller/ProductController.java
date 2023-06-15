package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Product;

import com.xadmin.DepartmentalStore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
@Autowired
    private ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */

    @Operation(operationId = "getAllProducts", summary = "Get All Product Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all orders"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
@GetMapping("/products")
    public List<Product> getAllProducts(){  return productService.getAllProducts();  }


    /**
     * Add product details.
     *
     * @param product the product to be added
     * @return a message indicating the successful creation of the product
     */
    @Operation(operationId = "addProduct", summary = "Add Product Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Added Product"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping("/products")
    public String addProduct(@RequestBody Product product)
    {  productService.addProduct(product);
    return "Product is created with ID : " + product.getProductId();
    }


    /**
     * Update product details with the given ID.
     *
     * @param id      the ID of the product to be updated
     * @param product the updated product details
     */
    @Operation(operationId = "updateProduct", summary = "Update product details with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated product with given id"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product)
    { productService.updateProduct(id,product); }


    /**
     * Delete product with the given ID.
     *
     * @param id the ID of the product to be deleted
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted product with given id"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id)
    { productService.deleteProduct(id); }

}
