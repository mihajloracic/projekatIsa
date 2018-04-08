package com.example.demo.controller;

import com.example.demo.model.dto.EntityID;
import com.example.demo.service.ReservationSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservationSeat")
public class ReservationSeatController {

    @Autowired
    ReservationSeatService reservationSeatService;

    @RequestMapping(value="/findByEvent", method = RequestMethod.POST)
    public ResponseEntity<?> findByEvent(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(reservationSeatService.findReservedByEvent(entityID.getId()));
    }
}
