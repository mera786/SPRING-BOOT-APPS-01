package com.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
    private long id;
    private String name;
    private int numberOfBeds;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfGuestAllowed;
    private String city;
    private String area;
    private String state;

    private List<RoomsDto> rooms;
    

}
