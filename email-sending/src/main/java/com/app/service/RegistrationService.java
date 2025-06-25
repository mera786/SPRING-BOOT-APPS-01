package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.dto.EmailRequest;
import com.app.entities.Registration;
import com.app.repository.RegistrationRepository;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;


   public Registration addRegistration(Registration registration) {
    Registration saved = repository.save(registration);

    if (saved != null && saved.getId() != null) {
        EmailRequest request = new EmailRequest();
        request.setTo(saved.getEmail());
        request.setSubject("Registration Success!");
        request.setBody(saved);  // you can use this object if needed for logging or later

        // Use toString() to convert Registration to a string body
        String body = "Hi " + saved.getName() + ",\n\n"
                    + "Your registration is successful!\n\n"
                    + "Details:\n" + saved.toString() + "\n\nThank you.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(body);

        javaMailSender.send(message);
    } else {
        throw new RuntimeException("Data not saved");
    }

    return saved;
}


}
