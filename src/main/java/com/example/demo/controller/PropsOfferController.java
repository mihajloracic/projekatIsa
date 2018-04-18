package com.example.demo.controller;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.PropsOffer;
import com.example.demo.model.dto.PropsOfferDTO;
import com.example.demo.repository.PropsOfferRepository;
import com.example.demo.service.impl.NotificationService;
import com.example.demo.service.impl.PropsOfferService;
import com.example.demo.service.impl.UserGetterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/propsoffer")
public class PropsOfferController {
    @Autowired
    PropsOfferService propsOfferService;
    @Autowired
    UserGetterService userGetterService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    PropsOfferRepository propsOfferRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPropsOffer(HttpServletRequest request, @RequestBody PropsOfferDTO propsOfferDTO) throws NotFoundException {
        propsOfferDTO.setUser(userGetterService.getLoggedInUser(request));
        propsOfferService.addUpdateOffer(propsOfferDTO);
        return ResponseEntity.ok(propsOfferDTO);
    }
    @Transactional
    @RequestMapping(method = RequestMethod.POST,value = "/accept")
    public ResponseEntity<?> acceptOffer(HttpServletRequest request, @RequestBody PropsOfferDTO propsOfferDTO) throws NotFoundException {
        propsOfferService.verfyValue(propsOfferDTO);
        propsOfferService.sellItem(propsOfferDTO);
        return ResponseEntity.ok(propsOfferDTO);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProps(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(propsOfferService.getAllOffers());
    }
    @RequestMapping(method = RequestMethod.GET,value = "/byprops")
    public ResponseEntity<?> getOffersByProps(@RequestParam("propsId") long id) {
        return ResponseEntity.ok(propsOfferService.getOffersByProps(id));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user")
    public ResponseEntity<?> getMyOffers(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(propsOfferService.getMyOffers(userGetterService.getLoggedInUser(request)));
    }

}
