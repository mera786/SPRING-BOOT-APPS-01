package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name="property")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "number_of_beds")
    private int numberOfBeds;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "number_of_bathrooms")
    private int numberOfBathrooms;

    @Column(name = "number_of_guests_allowed")
    private int numberOfGuestAllowed;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

       @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rooms> rooms = new ArrayList<>();

}
