package com.app.dto;

import com.app.entities.Registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailRequest {

    private String to;
    private String subject;
    private Registration body;
}
