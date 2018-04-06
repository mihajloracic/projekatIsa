package com.example.demo.service.impl;

import com.example.demo.domain.entity.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> getEventsByVenue(Long venueId) {
        return eventRepository.findEventsByVenue(venueId);
    }

    @Override
    public List<Event> getEventsByShow(Long showId) {
        List<Event> events = eventRepository.findEventsByShow(showId);
        return events;
    }

    @Override
    public List<Event> getEventsOfVenueByShow(Long venueId, Long showId) {
        return eventRepository.findEventsOfVenueByShow(venueId, showId);
    }


}
