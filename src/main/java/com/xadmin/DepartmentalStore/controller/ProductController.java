package com.xadmin.DepartmentalStore.controller;

import com.xadmin.DepartmentalStore.bean.Product;

import com.xadmin.DepartmentalStore.helper.MyExcelHelper;
import com.xadmin.DepartmentalStore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")

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

    @ApiOperation("Upload excel")
    @PostMapping(value = "/product/upload", headers = "content-type=multipart/form-data")
    public ResponseEntity<?> upload(
            @ApiParam(value = "Excel file uploaded", required = true)
            @RequestParam("file") MultipartFile file) {

        if(MyExcelHelper.checkExcel(file)) {

            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved!" ));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel");
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
