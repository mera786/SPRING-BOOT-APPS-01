package com.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
    private long id;
    private String name;
    private int numberOfRooms;

    private List<String> imageUrls;

}
