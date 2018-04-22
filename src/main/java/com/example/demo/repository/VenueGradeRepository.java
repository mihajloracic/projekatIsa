package com.example.demo.repository;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.entity.VenueGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VenueGradeRepository extends JpaRepository<VenueGrade,Long>{

    public VenueGrade findByUsernameAndVenue(String username,Venue venue);


    @Query("SELECT coalesce (AVG(grade),0) FROM VenueGrade WHERE id = :id")
    public double getVenueAverageGrade(@Param("id") Long id);
}
