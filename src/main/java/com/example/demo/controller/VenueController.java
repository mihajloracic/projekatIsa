package com.example.demo.controller;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.type.VenueType;
import com.example.demo.model.dto.VenueTypeNameDTO;
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

    @RequestMapping(value = "/theatres", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTheatres(){
        return  ResponseEntity.ok(venueService.findByType(VenueType.THEATRE));
    }

    @RequestMapping(value = "/theatresByName", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTheatresByNameAsc(){ return  ResponseEntity.ok(venueService.findAllOrderedByName(VenueType.THEATRE)); }

    @RequestMapping(value = "/theatresByCity", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTheatresByCityAsc(){ return  ResponseEntity.ok(venueService.findAllOrderedByCity(VenueType.THEATRE)); }

    @RequestMapping(value = "/cinemas", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        return  ResponseEntity.ok(venueService.findByType(VenueType.CINEMA));
    }

    @RequestMapping(value = "/cinemasByName", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemasByNameAsc(){ return  ResponseEntity.ok(venueService.findAllOrderedByName(VenueType.CINEMA)); }

    @RequestMapping(value = "/cinemasByCity", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemasByCityAsc(){ return  ResponseEntity.ok(venueService.findAllOrderedByCity(VenueType.CINEMA)); }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVenue(@RequestBody Venue v){
        venueService.addVenue(v);
        return  ResponseEntity.ok(v);
    }

    @RequestMapping(value = "/getByTypeAndName", method = RequestMethod.POST)
    public ResponseEntity<?> getVenuesByTypeAndNameContaining(@RequestBody VenueTypeNameDTO venueTypeNameDTO){
        return  ResponseEntity.ok(venueService.findByTypeAndName(venueTypeNameDTO.getVenueType(), venueTypeNameDTO.getName()));
    }


}
