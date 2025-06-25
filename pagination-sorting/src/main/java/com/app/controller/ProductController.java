package com.app.controller;

import com.app.dto.ProductResponse;
import com.app.entity.Product;
import com.app.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // http://localhost:8081/products/save
    @PostMapping("/save")
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product saveProduct = productService.createProduct(product);
        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    // To get the data of list in the view of pagination :
    // http://localhost:8081/products?pageNo=0&pageSize=7


//To get the data of list in the view of sorting :
    // http://localhost:8081/products?pageNo=0&pageSize=10&sortBy=brand&sortDir=asc
    @GetMapping
    public ProductResponse getAllProducts(
            @RequestParam(value = "pageNo",defaultValue = "0",required
                    = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue =
                    "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required
                    = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue =
                    "asc",required = false) String sortDir
    ){
        return productService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
    }
}
