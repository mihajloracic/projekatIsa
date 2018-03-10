package com.example.demo.service.impl;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.FriendshipRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendshipService;
import com.sun.corba.se.impl.resolver.FileResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.utils.FriendshipUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipUtils friendshipUtils;

//    @Autowired
//    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
//        this.friendshipRepository = friendshipRepository;
//        this.userRepository = userRepository;
//    }

    @Override
    public void insertFriendship(Long userOne, Long userTwo, int status, Long lastActionUser) {
        Friendship friendship = friendshipUtils.prepareInsert(userOne, userTwo, status, lastActionUser);
        friendshipRepository.save(friendship);
    }

    @Override
    public void deleteFriendship(Long userOne, Long userTwo) {
        Friendship friendship;
        if (userOne < userTwo) {
            friendship = friendshipRepository.findByUserOneIdAndUserTwoId(userOne, userTwo);
        } else {
            friendship = friendshipRepository.findByUserOneIdAndUserTwoId(userTwo, userOne);
        }
        friendshipRepository.delete(friendship.getId());
    }


    @Override
    public List<Friendship> getFriendships() {
        return friendshipRepository.findAll();
    }

    @Override
    public List<Friendship> getFriendshipsFromUser(Long user) {
        return friendshipRepository.findByUserOneIdOrUserTwoIdAndStatus(user, user, 1);
    }




}
