package com.example.demo.service;

import com.example.demo.domain.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsersByIds(List<Long> ids);

    User findByUsername(String username);

    User saveUser(User user);

    List<User> findByFirstnameOrLastname(String name);

    List<User> findByFirstnameAndLastname(String firstname, String lastname);

}
