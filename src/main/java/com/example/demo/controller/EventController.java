package com.example.demo.controller;

import com.example.demo.model.dto.EntityID;
import com.example.demo.model.dto.VenueShowDTO;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value="/findByVenue", method = RequestMethod.POST)
    public ResponseEntity<?> findByVenue(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventsByVenue(entityID.getId()));
    }

    @RequestMapping(value="/findByShow", method = RequestMethod.POST)
    public ResponseEntity<?> findByShow(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventsByShow(entityID.getId()));
    }

    @RequestMapping(value="/findByVenueShow", method = RequestMethod.POST)
    public ResponseEntity<?> findByVenueShow(@RequestBody VenueShowDTO venueShowDTO) {
        return ResponseEntity.ok(eventService.getEventsOfVenueByShow(venueShowDTO.getVenueId(), venueShowDTO.getShowId()));
    }

}
