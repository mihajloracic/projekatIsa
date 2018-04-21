package com.example.demo.controller;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ReservationSeat;
import com.example.demo.model.dto.DiscountReservationDTO;
import com.example.demo.model.dto.EntityID;
import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationSeatDTO;
import com.example.demo.service.ReservationSeatService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.impl.DiscountEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    DiscountEventService discountEventService;

    @Autowired
    ReservationSeatService reservationSeatService;

    @RequestMapping(value="/findByEvent", method = RequestMethod.POST)
    public ResponseEntity<?> findByEvent(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(reservationService.findByEvent(entityID.getId()));
    }

    @RequestMapping(value="/findById", method = RequestMethod.POST)
    public ResponseEntity<?> findById(@RequestBody EntityID entityID) {
        return ResponseEntity.ok(reservationService.findById(entityID.getId()));
    }


    @RequestMapping(value="/discountReservation",
            method = RequestMethod.POST)
    public ResponseEntity<?> addDiscountReservation(@RequestBody DiscountReservationDTO reservationDTO) {
        Event e = discountEventService.getEventById(reservationDTO.getDiscountEventId());

        List<ReservationSeat> reserved = reservationSeatService.findReservedByEvent(e.getId());
        int maxRow = e.getHall().getnRows();
        int maxCols= e.getHall().getnCols();
        ReservationDTO rDTO = new ReservationDTO();
        for(int row=0;row<maxRow;row++){
            for(int col=0;col<maxCols;col++){
                int taken = 0;
                for(ReservationSeat rs : reserved){
                    if(rs.getCol()==col && rs.getRow()==row){
                        taken = 1;
                    }
                }
                if(taken==0){
                    rDTO.setEventId(e.getId());
                    ReservationSeatDTO rsDTO = new ReservationSeatDTO();
                    rsDTO.setCol(col);
                    rsDTO.setRow(row);
                    List<ReservationSeatDTO> list = new ArrayList<>();
                    list.add(rsDTO);
                    rDTO.setSeats(list);
                    rDTO.setInvitedUsernames(new ArrayList<>());
                    rDTO.setUsername(reservationDTO.getUsername());


                    Reservation res = reservationService.addReservation(rDTO);
                    return ResponseEntity.ok(res);
                }
            }
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @RequestMapping(value="/addReservation",
                    method = RequestMethod.POST)
    public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation res = reservationService.addReservation(reservationDTO);
        return ResponseEntity.ok(res);
    }


}
