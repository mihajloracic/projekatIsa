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
import java.util.stream.Collectors;

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

    @Override
    public List<User> getFriendsFromUserOrderedByFirstname(Long user) {
        ArrayList<Friendship> friendships = (ArrayList<Friendship>)friendshipService.getFriendshipsFromUser(user);
        List<Long> ids = utils.getFriendIdList(user, friendships);

        return userRepository.findByIdInOrderByFirstname(ids);
    }

    @Override
    public List<User> getFriendsFromUserOrderedByLastname(Long user) {
        ArrayList<Friendship> friendships = (ArrayList<Friendship>)friendshipService.getFriendshipsFromUser(user);
        List<Long> ids = utils.getFriendIdList(user, friendships);

        return userRepository.findByIdInOrderByLastname(ids);
    }

    @Override
    public List<User> getNonFriendsFromUser(Long user) {
        ArrayList<Friendship> friendships = (ArrayList<Friendship>)friendshipService.getInvalidToAdd(user);
        List<Long> ids = utils.getFriendIdList(user, friendships);
        ids.add(user);

        return userRepository.findByIdNotIn(ids);
    }

    @Override
    public List<User> getUserFriendRequests(Long user) {
        ArrayList<Friendship> friendships = (ArrayList<Friendship>)friendshipService.getFriendRequestsFromUser(user);
        List<Long> ids = utils.getFriendIdList(user, friendships);

        return userRepository.findByIdIn(ids);
    }

    @Override
    public List<User> searchUsers(List<User> users, String queryParam) {
        if (queryParam.contains(" ")) {
            String[] arr = queryParam.split(" ");
            List<User> ret = users
                    .stream()
                    .filter(elem -> (elem.getFirstname().toLowerCase().contains(arr[0].trim())
                            || elem.getFirstname().toLowerCase().contains(arr[1].trim())
                            || elem.getLastname().toLowerCase().contains(arr[0].trim())
                            || elem.getLastname().toLowerCase().contains(arr[1].trim())))
                    .collect(Collectors.toList());

            return (ret != null) ? ret : new ArrayList<>();
        } else {
            List<User> ret = users
                    .stream()
                    .filter(elem -> (elem.getFirstname().toLowerCase().contains(queryParam)
                            || elem.getLastname().toLowerCase().contains(queryParam)))
                    .collect(Collectors.toList());

            return (ret != null) ? ret : new ArrayList<>();
        }
    }
}
