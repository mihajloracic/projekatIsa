package com.example.demo.service;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;

import java.util.List;


public interface FriendshipService {

    Friendship insertFriendship(Long userOne, Long userTwo, int status, Long lastActionUser);

    void deleteFriendship(Long userOne, Long userTwo);

    void updateFriendship(Long userOne, Long userTwo, int status, Long lastActionUser);

    List<Friendship> getFriendships();

    List<Friendship> getFriendshipsFromUser(Long user);

    List<Friendship> getFriendRequestsFromUser(Long user);

    List<Friendship> getInvalidToAdd(Long user);

}
