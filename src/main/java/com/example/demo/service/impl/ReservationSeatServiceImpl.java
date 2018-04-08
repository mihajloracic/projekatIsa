package com.example.demo.service.impl;

import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ReservationSeat;
import com.example.demo.repository.ReservationSeatRepository;
import com.example.demo.service.ReservationSeatService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationSeatServiceImpl implements ReservationSeatService {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationSeatRepository reservationSeatRepository;

    @Override
    public List<ReservationSeat> saveSeats(List<ReservationSeat> seats) {
        return reservationSeatRepository.save(seats);
    }

    @Override
    public ReservationSeat addSeat(ReservationSeat reservationSeat) {
        return reservationSeatRepository.save(reservationSeat);
    }

    @Override
    public ReservationSeat findByEventAndSeat(Long eventId, int row, int col) {
        return null;
    }

    @Override
    public List<ReservationSeat> findReservedByEvent(Long eventId) {
        List<Reservation> reservations = reservationService.findByEvent(eventId);
        List<Long> ids = reservations.stream().map(res -> res.getId()).collect(Collectors.toList());
        return reservationSeatRepository.findReserved(ids);
    }
}
