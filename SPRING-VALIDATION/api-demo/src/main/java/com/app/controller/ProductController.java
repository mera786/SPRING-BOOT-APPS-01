package com.app.controller;


import com.app.entities.Product;
import com.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    /*

     json for testing

{
"name": "Sample Product",
"price": 10.99,
"quantity": 5,
"sellerEmail": "seller@example.com",
"manufactureDate": "2024-02-03",
"expiryDate": "2025-02-03",
"tags": "Electronics, Gadgets"
}
     */

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product,
                                           BindingResult result){
        if(result.hasErrors()){
            return new

                    ResponseEntity<>(result.getFieldError().getDefaultMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Product saveProduct = productService.createProduct(product);
        return new ResponseEntity<>( saveProduct, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody @Valid Product product) {
        Product updatedProduct = productService.updateProduct(id,
                product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
