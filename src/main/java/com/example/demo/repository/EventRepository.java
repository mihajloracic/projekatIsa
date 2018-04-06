package com.example.demo.repository;

import com.example.demo.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value = "SELECT * FROM event \n" +
            "WHERE venue_id = :venueId", nativeQuery = true)
    List<Event> findEventsByVenue(
            @Param("venueId") Long venueId
    );


    @Query(value = "SELECT * FROM event \n" +
            "WHERE show_id = :showId", nativeQuery = true)
    List<Event> findEventsByShow(
            @Param("showId") Long showId
    );

    @Query(value = "SELECT * FROM event \n" +
            "WHERE venue_id = :venueId AND show_id = :showId", nativeQuery = true)
    List<Event> findEventsOfVenueByShow(
            @Param("venueId") Long venueId,
            @Param("showId") Long showId
    );
}
