package com.example.demo.controller;


import com.example.demo.domain.entity.User;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/velas")
public class TestController {


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest( ) {
        UserDetails user = userDetailsService.loadUserByUsername("user");
        String password = user.getPassword();
        String username = user.getUsername();
        return ResponseEntity.ok(new String("poy od Velas i laku noc\n " + "USERNAME :" + username + "PASS :" + password));
    }
}
