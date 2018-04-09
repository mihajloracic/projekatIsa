package com.example.demo.service;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.User;

import javax.mail.MessagingException;
import java.util.List;

public interface ReservationEmailService {

    void sendEmailToInvited(User initiator, Event event, List<User> invited, Long reservationId);

    void sendReservationDetailsToInitiator(User initiator, Event event, List<User> invited);
}
