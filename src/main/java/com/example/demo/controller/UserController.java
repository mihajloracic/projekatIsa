package com.example.demo.controller;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getByUsername", method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }


    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    public User editUserInfo(@RequestBody User user) {
        User u = userService.findByUsername(user.getUsername());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setEmail(user.getEmail());
        u.setCity(user.getCity());
        u.setPhonenumber(user.getPhonenumber());
        return userService.saveUser(u);
    }

}
