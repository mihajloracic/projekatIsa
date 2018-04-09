package com.example.demo.service.impl;

import com.example.demo.domain.entity.Event;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.type.EventType;
import com.example.demo.service.ReservationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationEmailServiceImpl implements ReservationEmailService {

    @Value("${server.port}")
    String port;

    @Autowired
    EmailService emailService;

    @Override
    public void sendEmailToInvited(User initiator, Event event, List<User> invited, Long reservationId) {

        String invitationUrl = "localhost:"+ port + "/api/invitation.html?resId=" + reservationId;

        String subject = initiator.getFirstname() + " " + initiator.getLastname() +
                " vas je pozvao na " + event.getShow().getName();
        String message = "Kliknite na sledeći link kako biste potvrdili/odbili poziv!" +
                "\n" + "link : " + invitationUrl;

        invited.stream().forEach(u -> {
            try {
                emailService.sendNotificaitionAsync(u.getEmail(), subject, message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void sendReservationDetailsToInitiator(User initiator, Event event, List<User> invited) {
        String invitedStr = invited.stream()
                                    .map(u -> u.getFirstname() + " " + u.getLastname())
                                    .collect(Collectors.joining("\n"));

        String subject = "Rezervacija mesta za " + event.getShow().getName() + " uspešna!";
        String message = "Detalji:\n" +
                "Naziv " + ((event.getEventType() == EventType.PROJECTION) ? "filma" : "predstave") + " : " + event.getShow().getName() + "\n" +
                "Datum : " + event.getDate() + "\n" +
                "Vreme : " + event.getTime() + "\n" +
                "Sala : " + event.getHall().getName() + "\n" +
                "\n\n" +
                "Pozvani prijatelji : " + invitedStr;

        try {
            emailService.sendNotificaitionAsync(initiator.getEmail(), subject, message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
