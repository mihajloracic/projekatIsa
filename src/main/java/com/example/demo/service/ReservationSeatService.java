package com.example.demo.service;

import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ReservationSeat;

import java.util.List;

public interface ReservationSeatService {

    List<ReservationSeat> saveSeats(List<ReservationSeat> seats);

    ReservationSeat addSeat(ReservationSeat reservationSeat);

    ReservationSeat findByEventAndSeat(Long eventId, int row, int col);

    List<ReservationSeat> findReservedByEvent(Long eventId);


}
