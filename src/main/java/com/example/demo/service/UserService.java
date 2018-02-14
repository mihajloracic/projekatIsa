package com.example.demo.service;

import com.example.demo.domain.entity.User;

public interface UserService {

    User registerUser(User user);

//    void createVerificationToken(User user, String token);

    User saveUser(User user);

}
