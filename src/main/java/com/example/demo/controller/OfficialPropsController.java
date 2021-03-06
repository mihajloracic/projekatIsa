package com.example.demo.controller;

import com.example.demo.domain.entity.OfficialProps;
import com.example.demo.domain.entity.User;
import com.example.demo.service.impl.OfficialPropsService;
import com.example.demo.service.impl.UserGetterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/officialprops")
public class OfficialPropsController {

    @Autowired
    UserGetterService userGetterService;

    @Autowired
    OfficialPropsService officialPropsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps(HttpServletRequest request, @RequestBody OfficialProps props) throws NotFoundException {
        User user = userGetterService.getLoggedInUser(request);
        //TODO provera da li je user admin
        props.setUserCreated(user);
        officialPropsService.insertProps(props);
        return ResponseEntity.ok(props);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllProps() {
        return ResponseEntity.ok(officialPropsService.getAllProps());
    }

}
