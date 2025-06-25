package com.app.service;

import com.app.constant.AppConstants;
import com.app.dto.EmailRequest;
import com.app.entities.PropertyOrder;
import com.app.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;


    public PropertyOrder orderProperty(PropertyOrder order){
        PropertyOrder orderedProperty = repository.save(order);


        // kafka code
        EmailRequest request = new EmailRequest(order.getEmail(),"property added !","your details live");
        kafkaTemplate.send(AppConstants.TOPIC,request);

        return orderedProperty;
    }
}
