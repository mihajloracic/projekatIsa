package com.example.demo.service;

import com.example.demo.domain.entity.Reservation;
import com.example.demo.model.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<Reservation> findByEvent(Long eventId);

    boolean addReservation(ReservationDTO reservationDTO);


}


