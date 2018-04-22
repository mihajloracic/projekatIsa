package com.example.demo.service.impl;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.entity.VenueGrade;
import com.example.demo.repository.VenueGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueGradeService {

    @Autowired
    VenueGradeRepository venueGradeRepository;


    public void addGrade(VenueGrade venueGrade){

        VenueGrade existing = venueGradeRepository.findByUsernameAndVenue(venueGrade.getUsername(),venueGrade.getVenue());
        if(existing!= null){
            existing.setGrade(venueGrade.getGrade());
            venueGradeRepository.save(existing);
        }else{
            venueGradeRepository.save(venueGrade);
        }

    }

    public double getAverageGrade(Long venueId){
        return venueGradeRepository.getVenueAverageGrade(venueId);
    }

}
