package com.example.demo.service.impl;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendshipService;
import com.example.demo.service.UserFriendshipService;
import com.example.demo.utils.FriendshipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFriendshipServiceImpl implements UserFriendshipService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    FriendshipUtils utils;

    @Override
    public List<User> getFriendsFromUser(Long user) {
        ArrayList<Friendship> friendships = (ArrayList<Friendship>)friendshipService.getFriendshipsFromUser(user);
        List<Long> ids = utils.getFriendIdList(user, friendships);

        return userRepository.findByIdIn(ids);
    }
}
