package com.example.demo.controller;

import com.example.demo.domain.entity.DiscountEvent;
import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Hall;
import com.example.demo.domain.type.EventType;
import com.example.demo.model.dto.DiscountDTO;
import com.example.demo.model.dto.EntityID;
import com.example.demo.model.dto.EventDTO;
import com.example.demo.model.dto.VenueShowDTO;
import com.example.demo.service.EventService;
import com.example.demo.service.ShowService;
import com.example.demo.service.impl.DiscountEventService;
import com.example.demo.service.impl.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.AppUtils.distinctByKey;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    ShowService showService;

    @Autowired
    HallService hallService;

    @Autowired
    DiscountEventService discountEventService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addEvent(@RequestBody EventDTO data){
        Event e = new Event();
        e.setTime(data.getTime());
        e.setDate(data.getDate());
        e.setPrice(data.getPrice());
        Hall h =hallService.findById(data.getHallId());
        e.setHall(h);
        e.setShow(showService.findById(data.getShowId()));
        e.setVenue(h.getVenue());
        e.setEventType(EventType.PERFORMANCE);
        return ResponseEntity.ok(eventService.addEvent(e));
    }

    @RequestMapping(value="/findById", method = RequestMethod.POST)
    public ResponseEntity<?> findById(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventById(entityID.getId()));
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Event data) {
        Event existing = eventService.getEventById(id);
        existing.setDate(data.getDate());
        existing.setTime(data.getTime());
        existing.setPrice(data.getPrice());
        eventService.addEvent(existing);
        return ResponseEntity.ok(existing);
    }

    @RequestMapping(value="/findByVenue", method = RequestMethod.POST)
    public ResponseEntity<?> findByVenue(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(eventService.getEventsByVenue(entityID.getId()));
    }

    @RequestMapping(value="/venue/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findByVenueId(@PathVariable("id") long id) {
        return ResponseEntity.ok(eventService.getEventsByVenue(id));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable("id") long id) {
        Event e = eventService.getEventById(id);
        return ResponseEntity.ok(e);
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

    @RequestMapping(value="/discount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setOnDiscount(@RequestBody DiscountDTO data){
        DiscountEvent de = new DiscountEvent();
        de.setEvent(eventService.getEventById(data.getEventId()));
        de.setNewPrice(data.getPrice());
        return ResponseEntity.ok(discountEventService.addDiscount(de));
    }

}
