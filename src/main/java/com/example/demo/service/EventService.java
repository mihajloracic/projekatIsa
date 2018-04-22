package com.example.demo.service;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Venue;

import java.util.List;

public interface EventService {


    Event getEventById(Long id);

    List<Event> getEventsByVenue(Long venueId);

    List<Event> getEventsByShow(Long showId);

    List<Event> getEventsOfVenueByShow(Long venueId, Long showId);

    Event addEvent(Event e);

}
