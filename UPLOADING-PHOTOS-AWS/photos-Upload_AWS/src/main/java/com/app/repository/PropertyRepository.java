package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Property;

public interface PropertyRepository extends JpaRepository<Property,Long>{

}
