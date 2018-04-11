package com.example.demo.controller;

import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.type.VenueType;
import com.example.demo.model.dto.VenueTypeNameDTO;
import com.example.demo.service.impl.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController  {

    @Autowired
    VenueService venueService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllVenues(){
        return  ResponseEntity.ok(venueService.findAll());
    }

    @RequestMapping(value = "/theatres", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTheatres(){
        return  ResponseEntity.ok(venueService.findByType(VenueType.THEATRE));
    }

    @RequestMapping(value = "/cinemas", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        return  ResponseEntity.ok(venueService.findByType(VenueType.CINEMA));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVenue(@RequestBody Venue v){
        venueService.addVenue(v);
        return  ResponseEntity.ok(v);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") long id){

        Venue v = venueService.findById(id);
        return ResponseEntity.ok(v);

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> updateVenue(@RequestBody Venue v){
        venueService.updateVenue(v);
        return  ResponseEntity.ok(v);
    }

    @RequestMapping(value = "/getByTypeAndName", method = RequestMethod.POST)
    public ResponseEntity<?> getVenuesByTypeAndNameContaining(@RequestBody VenueTypeNameDTO venueTypeNameDTO){
        return  ResponseEntity.ok(venueService.findByTypeAndName(venueTypeNameDTO.getVenueType(), venueTypeNameDTO.getName()));
    }



}
