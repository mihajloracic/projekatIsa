package com.example.demo.controller;


import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.PropsService;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/velas")
public class TestController {

    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    PropsService propsService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;



}
