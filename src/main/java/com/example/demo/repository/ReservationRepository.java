package com.example.demo.repository;

import com.example.demo.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query(value = "SELECT * FROM reservation \n" +
            "WHERE event_id = :eventId", nativeQuery = true)
    List<Reservation> findByEvent(
            @Param("eventId") Long eventId
    );
}

