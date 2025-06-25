package com.app.repository;

import com.app.entities.PropertyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<PropertyOrder,Long> {
}
