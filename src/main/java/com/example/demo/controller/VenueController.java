package com.example.demo.controller;

import com.example.demo.domain.entity.DiscountEvent;
import com.example.demo.domain.entity.Hall;
import com.example.demo.domain.entity.Venue;
import com.example.demo.domain.type.VenueType;
import com.example.demo.model.dto.VenueTypeNameDTO;
import com.example.demo.service.impl.DiscountEventService;
import com.example.demo.service.impl.HallService;
import com.example.demo.service.impl.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/venues")
public class VenueController  {

    @Autowired
    VenueService venueService;

    @Autowired
    HallService hallService;

    @Autowired
    DiscountEventService discountEventService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
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

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") long id){

        Venue v = venueService.findById(id);
        return ResponseEntity.ok(v);

    }

    @RequestMapping(value="/{id}/halls",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getHalls(@PathVariable("id") long id){
        Venue v = venueService.findById(id);
        return ResponseEntity.ok(v.getHalls());
    }

    @RequestMapping(value="/hall/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getHallbyId(@PathVariable("id") long id){
        return ResponseEntity.ok(hallService.findById(id));
    }

    @RequestMapping(value="update/{id}",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('VENUEADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody Venue data){
        Venue existing = venueService.findById(id);
        existing.setAddress(data.getAddress());
        existing.setName(data.getName());
        existing.setDescription(data.getDescription());
        existing.setLat(data.getLat());
        existing.setLng(data.getLng());
        venueService.addVenue(existing);
        return ResponseEntity.ok(existing);
    }

    @RequestMapping(value="hall/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateHall(@PathVariable("id") long id,@RequestBody Hall data){
        Hall existing = hallService.findById(id);
        existing.setnCols(data.getnCols());
        existing.setnRows(data.getnRows());
        hallService.addHall(existing);
        return ResponseEntity.ok(existing);
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


    @RequestMapping(value="/{id}/discounts",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDiscounts(@PathVariable("id") long id){
        List<DiscountEvent>  list = discountEventService.getAll();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        List<DiscountEvent> ret = list.stream().filter(el -> el.getEvent().getVenue().getId() == id &&
                el.getEvent().getDate().compareTo(sqlDate) > 0  ).collect(Collectors.toList());

        return ResponseEntity.ok(ret);
    }

}
