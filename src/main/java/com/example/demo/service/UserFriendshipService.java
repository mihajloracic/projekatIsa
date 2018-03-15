package com.example.demo.service;

import com.example.demo.domain.entity.User;

import java.util.List;

public interface UserFriendshipService {

    List<User> getFriendsFromUser(Long user);

    List<User> getFriendsFromUserOrderedByFirstname(Long user);

    List<User> getFriendsFromUserOrderedByLastname(Long user);

}
