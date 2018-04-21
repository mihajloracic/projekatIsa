package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserFriendshipService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFriendshipService userFriendshipService;

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        return userRepository.findByIdIn(ids);
    }

    @Override
    public List<User> getUsersByUsernames(List<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    @Override
    public User findByUsername(String username) { return userRepository.findByUsernameIgnoreCase(username); }

    @Override
    public User saveUser(User user) { return userRepository.save(user); }

    @Override
    public List<User> findByFirstnameOrLastname(String name) {
        return userRepository.findDistinctByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name);
    }

    @Override
    public List<User> findByFirstnameAndLastname(String firstname, String lastname) {
        return userRepository.findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(firstname, lastname);
    }

}
