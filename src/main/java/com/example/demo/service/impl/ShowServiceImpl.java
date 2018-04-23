package com.example.demo.service.impl;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Show;
import com.example.demo.repository.ShowRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.ShowService;
import com.example.demo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    EventService eventService;

    @Autowired
    ShowRepository showRepository;

    @Override
    public List<Show> getMoviesFromCinemaEvents(Long cinemaId) {
        List<Event> events = eventService.getEventsByVenue(cinemaId);
        return events.stream().map(event -> event.getShow()).collect(Collectors.toList());
    }

    @Override
    public List<Show> getAll() {
        return showRepository.findAll();
    }

    @Override
    public Show findById(Long id) {
        return showRepository.findOne(id);
    }

    @Override
    public Show addShow(Show s) {
        return showRepository.save(s);
    }
}
