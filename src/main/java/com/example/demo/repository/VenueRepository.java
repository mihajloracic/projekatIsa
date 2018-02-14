package com.example.demo.repository;

import com.example.demo.domain.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue,Long>{


}
