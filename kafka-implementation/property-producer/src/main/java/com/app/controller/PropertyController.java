package com.app.controller;

import com.app.dto.APIResponse;
import com.app.entities.PropertyOrder;
import com.app.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyService service;




    // http://localhost:8081/api/v1/property/add-property
    @PostMapping(value = "/add-property")
    public ResponseEntity<?> orderProperty(@RequestBody PropertyOrder order){
        PropertyOrder propertyOrder = service.orderProperty(order);


        // Create response object
        APIResponse<PropertyOrder> response = new APIResponse<>();
        response.setMessage("Property added");
        response.setStatus(201);
        response.setData(propertyOrder);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
