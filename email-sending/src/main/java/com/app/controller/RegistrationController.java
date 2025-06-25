package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.EmailRequest;
import com.app.entities.Registration;
import com.app.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    // http://localhost:9090/api/registrations/add-register
    @PostMapping(value = "add-register")
    public ResponseEntity<Registration> registerUser(@RequestBody Registration registration) {
        Registration saved = registrationService.addRegistration(registration);

        // Optionally wrap in EmailRequest if needed elsewhere
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(saved.getEmail());
        emailRequest.setSubject("Registration Successful");
        emailRequest.setBody(saved);

        return ResponseEntity.ok(saved);
    }
}
