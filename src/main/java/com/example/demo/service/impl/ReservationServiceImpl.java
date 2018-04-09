package com.example.demo.service.impl;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.Reservation;
import com.example.demo.domain.entity.ReservationSeat;
import com.example.demo.domain.entity.User;
import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationSeatDTO;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationEmailService reservationEmailService;

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
    public Reservation addReservation(ReservationDTO reservationDTO) {
        List<ReservationSeatDTO> seats = reservationDTO.getSeats();
        Event event = eventService.getEventById(reservationDTO.getEventId());
        User user = userService.findByUsername(reservationDTO.getUsername());
        List<User> invited = userService.getUsersByUsernames(reservationDTO.getInvitedUsernames());


        Reservation newReservation = reservationRepository.save(new Reservation(user, event));

        // slanje mejla NAKON uspjesne transakcije
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                if (!invited.isEmpty()) {
                    reservationEmailService.sendEmailToInvited(user, event, invited, newReservation.getId());
                }
                reservationEmailService.sendReservationDetailsToInitiator(user, event, invited);
            }
        });


        List<ReservationSeat> resSeats = seats.stream()
                .map(s -> new ReservationSeat(newReservation, event, s.getRow(), s.getCol()))
                .collect(Collectors.toList());
        reservationSeatService.saveSeats(resSeats);

        return newReservation;
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationRepository.findOne(reservationId);
    }

}
