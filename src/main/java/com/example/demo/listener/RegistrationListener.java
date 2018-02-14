package com.example.demo.listener;

import com.example.demo.domain.entity.User;
import com.example.demo.event.OnRegistrationCompleteEvent;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationTokenService;
import com.example.demo.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    @Autowired
    VerificationTokenService tokenService;

    @Autowired
    EmailService emailService;

    @Value("${server.port}")
    String port;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        tokenService.createVerificationToken(user, token);

        String recipientMail = user.getEmail();
        String subject = "Potvrda registracije";
        String confirmationUrl = "localhost:"+ port + event.getUrl() + "/confirmRegistration.html?token=" + token;
        String link = "<a href='"+ confirmationUrl +"'>Kliknite ovdje</a>";

        String message = "<html><body>Kliknite na sledeci link kako biste aktivirali nalog<br>" + confirmationUrl + "</body></html>";

        try {
            emailService.sendNotificaitionAsync(recipientMail, subject, message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
