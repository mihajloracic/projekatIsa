package com.example.demo.service;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;

import java.util.List;


public interface FriendshipService {

    void insertFriendship(Long userOne, Long userTwo, int status, Long lastActionUser);

    void deleteFriendship(Long userOne, Long userTwo);

    List<Friendship> getFriendships();

    List<Friendship> getFriendshipsFromUser(Long user);
}
