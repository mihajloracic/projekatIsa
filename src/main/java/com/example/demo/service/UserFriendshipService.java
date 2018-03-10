package com.example.demo.service;

import com.example.demo.domain.entity.User;

import java.util.List;

public interface UserFriendshipService {

    List<User> getFriendsFromUser(Long user);

}
