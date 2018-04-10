package com.example.demo.repository;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.type.VenueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue,Long>{


    List<Venue> findByVenueType(VenueType type);

    List<Venue> findByVenueTypeAndNameContainingIgnoreCase(VenueType type, String name);

    List<Venue> findByVenueTypeOrderByNameAsc(VenueType type);

    List<Venue> findByVenueTypeOrderByCityAsc(VenueType type);
}
