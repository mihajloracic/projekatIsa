package com.example.demo.controller;

import com.example.demo.domain.entity.Venue;
import com.example.demo.service.impl.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController  {

    @Autowired
    VenueService venueService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllVenues(){

        return  ResponseEntity.ok(venueService.findAll());

    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVenue(@RequestBody Venue v){

        venueService.addVenue(v);
        return  ResponseEntity.ok(v);


    }

}
