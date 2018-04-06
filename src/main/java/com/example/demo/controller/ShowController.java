package com.example.demo.controller;

import com.example.demo.model.dto.EntityID;
import com.example.demo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @RequestMapping(value="/getMoviesFromCinema", method = RequestMethod.POST)
    public ResponseEntity<?> findByCinema(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(showService.getMoviesFromCinemaEvents(entityID.getId()));
    }
}
