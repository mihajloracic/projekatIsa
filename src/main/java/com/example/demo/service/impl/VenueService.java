package com.example.demo.service.impl;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.type.VenueType;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }

    public List<Venue> findAll(){
        return venueRepository.findAll();
    }

    public void addVenue(Venue v){
        venueRepository.save(v);
    }

    public void updateVenue(Venue v){
        venueRepository.save(v);
    }

    public Venue findById(Long id){
        return venueRepository.findOne(id);
    }

    public List<Venue> findByType(VenueType type) {
        return venueRepository.findByVenueType(type);
    }

    public List<Venue> findByTypeAndName(VenueType type, String name) {
        return venueRepository.findByVenueTypeAndNameContainingIgnoreCase(type, name.toLowerCase());
    }

}
