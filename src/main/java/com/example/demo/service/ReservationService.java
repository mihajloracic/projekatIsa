package com.example.demo.service;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Reservation;
import com.example.demo.model.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<Reservation> findByEvent(Long eventId);

    Reservation addReservation(ReservationDTO reservationDTO);

    Reservation findById(Long reservationId);

    List<Reservation> findAllByOwner(Long ownerId);

    List<Reservation> findReservationHistory(String username);
}


