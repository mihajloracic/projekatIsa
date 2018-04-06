package com.example.demo.service;

import com.example.demo.domain.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> getEventsByVenue(Long venueId);

    List<Event> getEventsByShow(Long showId);

    List<Event> getEventsOfVenueByShow(Long venueId, Long showId);

}
