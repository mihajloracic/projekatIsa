package com.example.demo.service.impl;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ReservationSeat;
import com.example.demo.domain.entity.User;
import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationSeatDTO;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.ReservationSeatService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationSeatService reservationSeatService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Override
    public List<Reservation> findByEvent(Long eventId) {
        return reservationRepository.findByEvent(eventId);
    }

    @Override
    @Transactional( readOnly = false,
                    propagation = Propagation.REQUIRED,
                    isolation = Isolation.SERIALIZABLE)
    public boolean addReservation(ReservationDTO reservationDTO) {
        List<ReservationSeatDTO> seats = reservationDTO.getSeats();
        List<String> invitedUsernames = reservationDTO.getInvitedUsernames();
        //dodaj za rezervaciju da ima one to many za usere
        //onda i njih sacuvaj

        Event event = eventService.getEventById(reservationDTO.getEventId());
        User user = userService.findByUsername(reservationDTO.getUsername());

        Reservation newReservation = reservationRepository.save(new Reservation(user, event));
        List<ReservationSeat> resSeats = seats.stream()
                .map(s -> new ReservationSeat(newReservation, event, s.getRow(), s.getCol()))
                .collect(Collectors.toList());

        reservationSeatService.saveSeats(resSeats);
        return true;
    }

}
