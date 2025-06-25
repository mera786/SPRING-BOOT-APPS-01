package com.app.controller;

import com.app.entities.Product;
import com.app.helper.ExcelHelper;
import com.app.service.ProductService;
import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;


    // http://localhost:8081/upload
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam ("file")MultipartFile file)
            throws IOException {
        if(ExcelHelper.checkExcelFormats(file)){
            service.saveProduct(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("saved");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("upload only excell file");
        }
    }

    // http://localhost:8081
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = service.getProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
