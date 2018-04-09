package com.example.demo.controller;

import com.example.demo.domain.entity.ReservationSeat;
import com.example.demo.model.dto.EntityID;
import com.example.demo.service.ReservationSeatService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {


    @Autowired
    ReservationSeatService reservationSeatService;

    @RequestMapping(value="/accept", method = RequestMethod.POST)
    public ResponseEntity<?> acceptInvitation(@RequestBody EntityID entityID) {
//        return ResponseEntity.ok(reservationService.findByEvent(entityID.getId()));
        return null;
    }

    @RequestMapping(value="/decline", method = RequestMethod.POST)
    public ResponseEntity<?> declineInvitation(@RequestBody EntityID entityID) {
        List<ReservationSeat> seatsFromReservation = reservationSeatService.findByReservation(entityID.getId());

        int minRow = seatsFromReservation.stream().map(ReservationSeat::getRow).min(Integer::compare).get();
        int minCol = seatsFromReservation.stream().map(ReservationSeat::getCol).min(Integer::compare).get();

        ReservationSeat toRemove = seatsFromReservation.stream()
                .filter(s -> s.getCol() == minCol && s.getRow() == minRow)
                .findFirst().get();

        reservationSeatService.removeSeat(toRemove.getId());

        return ResponseEntity.ok(toRemove);
    }

}
