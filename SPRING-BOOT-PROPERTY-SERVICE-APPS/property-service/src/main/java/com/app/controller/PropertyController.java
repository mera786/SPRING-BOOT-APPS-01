package com.app.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.APIResponse;
import com.app.dto.PropertyDto;
import com.app.service.PropertyService;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PropertyController.class);

// http://localhost:9091/api/v1/property

    @PostMapping(value = "/add-property")
    public ResponseEntity<?> addProperty(@RequestBody PropertyDto dto) {

        // Log the multipart parts
        logger.info("Property JSON: " + dto);

        // Process the property 
        PropertyDto property = propertyService.addProperty(dto);

        // Create response object
        APIResponse<PropertyDto> response = new APIResponse<>();
        response.setMessage("Property added");
        response.setStatus(201);
        response.setData(property);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

