package com.app.service;

import com.app.constant.AppConstants;
import com.app.dto.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestListener {

    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = AppConstants.TOPIC, groupId="group_customer_order")
    public void kafakSubscriberContent(String emailRequest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        EmailRequest emailContent= mapper.readValue(emailRequest, EmailRequest.class);

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(emailContent.getTo());
        sm.setSubject(emailContent.getSubject());
        sm.setText(emailContent.getBody());
        javaMailSender.send(sm);

    }
}
