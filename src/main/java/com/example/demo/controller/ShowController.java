package com.example.demo.controller;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Show;
import com.example.demo.model.dto.EntityID;
import com.example.demo.service.EventService;
import com.example.demo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasRole('VENUEADMIN')")
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

    @PreAuthorize("hasRole('VENUEADMIN')")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Show data) {
        Show show = new Show();
        show.setActors(data.getActors());
        show.setDescription(data.getDescription());
        show.setGenre(data.getGenre());
        show.setName(data.getName());
        show.setLength(data.getLength());
        show.setDirector(data.getDirector());
        return ResponseEntity.ok(showService.addShow(show));
    }


}
