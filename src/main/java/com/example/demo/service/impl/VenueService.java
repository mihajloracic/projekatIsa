package com.example.demo.service.impl;

import com.example.demo.domain.entity.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    VenueRepository venueRepository;


    public List<Venue> findAll(){
        return venueRepository.findAll();
    }

    public void addVenue(Venue v){
        venueRepository.save(v);
    }


}
