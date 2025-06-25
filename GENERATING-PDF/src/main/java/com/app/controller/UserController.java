package com.app.controller;


import com.app.entity.User;
import com.app.pdfGenerator.PDFService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private UserService service;

    // http://localhost:8081/user/save
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = service.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    // http://localhost:8081/user/generatePdf
    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<User> allUsers = service.getAllUsers();
        byte[] pdfBytes = pdfService.createPdf(allUsers);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "users.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
