package com.example.demo.controller;

import com.example.demo.model.security.CerberusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/currentUser")
public class SecurityController {


    @RequestMapping(value="/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        String name;
        try {
            name = principal.getName();
        } catch(Exception e) {
            return "";
        }
        return name;
    }

    @RequestMapping(value="/userDetails", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserDetails(Principal principal) {
        CerberusUser user = (CerberusUser) principal;
        return user.toString();
    }

}
