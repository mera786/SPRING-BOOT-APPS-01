package com.app.repository;

import com.app.entities.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long> {

     List<RoomAvailability> findByRoomId(long id);
}
