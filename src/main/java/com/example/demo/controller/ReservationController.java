package com.example.demo.controller;

import com.example.demo.domain.entity.Reservation;
import com.example.demo.model.dto.EntityID;
import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value="/findByEvent", method = RequestMethod.POST)
    public ResponseEntity<?> findByEvent(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(reservationService.findByEvent(entityID.getId()));
    }

    @RequestMapping(value="/findById", method = RequestMethod.POST)
    public ResponseEntity<?> findById(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(reservationService.findById(entityID.getId()));
    }

    @RequestMapping(value="/addReservation",
                    method = RequestMethod.POST)
    public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation res = reservationService.addReservation(reservationDTO);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value="/findAll", method = RequestMethod.POST)
    public ResponseEntity<?> findAllReservations(@RequestBody EntityID entityID) {
        List<Reservation> reservations = reservationService.findAllByOwner(entityID.getId());
        return ResponseEntity.ok(reservations);
    }


}
