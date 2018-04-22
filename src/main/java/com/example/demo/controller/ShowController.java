package com.example.demo.controller;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Show;
import com.example.demo.model.dto.EntityID;
import com.example.demo.service.EventService;
import com.example.demo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(showService.getAll());
    }

    @RequestMapping(value="/getMoviesFromCinema", method = RequestMethod.POST)
    public ResponseEntity<?> findByCinema(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(showService.getMoviesFromCinemaEvents(entityID.getId()));
    }

    @RequestMapping(value="/updateByProjectionId/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateByProjectionId(@PathVariable Long id, @RequestBody Show data) {
        Event event = eventService.getEventById(id);
        Show existing = event.getShow();
        existing.setActors(data.getActors());
        existing.setDescription(data.getDescription());
        existing.setGenre(data.getGenre());
        existing.setName(data.getName());
        existing.setLength(data.getLength());
        existing.setDirector(data.getDirector());
        showService.addShow(existing);
        return ResponseEntity.ok(existing);
    }

}
