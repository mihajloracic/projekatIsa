package com.example.demo.controller;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.entity.VerificationToken;
import com.example.demo.event.OnRegistrationCompleteEvent;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationTokenService;
import com.example.demo.service.impl.EmailService;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenService tokenService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user, WebRequest request){


        try {
            userService.registerUser(user);
        } catch (DataIntegrityViolationException e) {

            LOGGER.warn("Integrity constraint violated");
            return ResponseEntity.status(409).body(user);
        }

        try {
            String appUrl = request.getContextPath() + "/registration";
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));

        } catch(Exception e) {
            LOGGER.info(e.getMessage());
        }

        return ResponseEntity.ok(user);
    }


    @RequestMapping(value = "/confirmRegistration", method = RequestMethod.GET)
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = tokenService.findByToken(token);

        if(verificationToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header(HttpHeaders.LOCATION, "/api/").build();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header(HttpHeaders.LOCATION, "/api/").build();
        }

        user.setEnabled(true);
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/").build();
    }

}
