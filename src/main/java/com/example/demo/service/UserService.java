package com.example.demo.service;

import com.example.demo.domain.entity.User;

public interface UserService {

    User registerUser(User user);

    User saveUser(User user);

}
