package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration,Long>{

}
