package com.example.demo.controller;


import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/velas")
public class TestController {


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest( ) {
        String username = userDetailsService.loadUserByUsername("user").getPassword();
        return ResponseEntity.ok(new String("poy od Velas " + username));
    }
}
