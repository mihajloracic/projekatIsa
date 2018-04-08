package com.example.demo.controller;

import com.example.demo.domain.entity.Event;
import com.example.demo.model.dto.EntityID;
import com.example.demo.model.dto.VenueShowDTO;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.AppUtils.distinctByKey;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value="/findById", method = RequestMethod.POST)
    public ResponseEntity<?> findById(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventById(entityID.getId()));
    }

    @RequestMapping(value="/findByVenue", method = RequestMethod.POST)
    public ResponseEntity<?> findByVenue(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventsByVenue(entityID.getId()));
    }

    //DISTINCT BY SHOW
    @RequestMapping(value="/findByVenueDistinct", method = RequestMethod.POST)
    public ResponseEntity<?> findByVenueDistinct(@RequestBody EntityID entityID) {
        List<Event> events = eventService.getEventsByVenue(entityID.getId());
        events = events.stream().filter(distinctByKey(e -> e.getShow())).collect(Collectors.toList());
        return ResponseEntity.ok(events);
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
