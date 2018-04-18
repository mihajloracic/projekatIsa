package com.example.demo.controller;

import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.impl.UserGetterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    UserGetterService userGetterService;

    @Autowired
    NotificationRepository notificationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> acceptOffer(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(notificationRepository.findByUser(userGetterService.getLoggedInUser(request)));
    }
}
