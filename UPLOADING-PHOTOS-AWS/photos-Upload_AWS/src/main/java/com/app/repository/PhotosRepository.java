package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PropertyPhotos;

public interface PhotosRepository extends JpaRepository<PropertyPhotos,Long> {
}
