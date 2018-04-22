package com.example.demo.controller;

import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ShowGrade;
import com.example.demo.domain.entity.VenueGrade;
import com.example.demo.model.dto.GradeDTO;
import com.example.demo.service.ReservationService;
import com.example.demo.service.impl.ShowGradeService;
import com.example.demo.service.impl.VenueGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/grade")
public class GradeController {


    @Autowired
    ShowGradeService showGradeService;

    @Autowired
    VenueGradeService venueGradeService;

    @Autowired
    ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void grade(@RequestBody GradeDTO data){

        if(data.getShowGrade() > 5)
            data.setShowGrade(5);
        if(data.getVenueGrade() > 5)
            data.setVenueGrade(5);

        Reservation reservation = reservationService.findById(data.getReservationId());
        if(data.getShowGrade() != 0){
            ShowGrade showGrade = new ShowGrade();
            showGrade.setGrade(data.getShowGrade());
            showGrade.setUsername(reservation.getReservationOwner().getUsername());
            showGrade.setShow(reservation.getEvent().getShow());
            showGradeService.addGrade(showGrade);
        }

        if(data.getVenueGrade() != 0){
            VenueGrade venueGrade = new VenueGrade();
            venueGrade.setGrade(data.getShowGrade());
            venueGrade.setUsername(reservation.getReservationOwner().getUsername());
            venueGrade.setVenue(reservation.getEvent().getVenue());
            venueGradeService.addGrade(venueGrade);
        }
    }

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> showAverageGrade(@PathVariable Long id){
        return ResponseEntity.ok(showGradeService.getAverageGrade(id));
    }


    @RequestMapping(value="/venue/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> venueAverageGrade(@PathVariable Long id){
        return ResponseEntity.ok(venueGradeService.getAverageGrade(id));
    }

}
