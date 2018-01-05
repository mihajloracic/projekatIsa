package com.example.demo.controller;


import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import com.example.demo.security.PropsService;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/velas")
public class TestController {

    @Autowired
    PropsService propsService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest( ) {
        UserDetails user = userDetailsService.loadUserByUsername("user");
        String password = user.getPassword();
        String username = user.getUsername();
        return ResponseEntity.ok(new String("poy od Velas i laku noc\n " + "USERNAME :" + username + "PASS :" + password));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps() {
        Props props = new Props();
        props.setName("Reket");
        props.setDescription("Jako je super bolji od djovakovog");
        props.setApproved(false);
        props.setExpirationDate(new Date(123));
        propsService.insertProps(props);
        return ResponseEntity.ok(new String("Uspesno kreiran"));
    }

}
