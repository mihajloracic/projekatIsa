package com.example.demo.utils;

import com.example.demo.domain.entity.Friendship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class FriendshipUtils {

    public Friendship prepareInsert(Long userOne, Long userTwo, int status, Long lastActionUser) {
        if (userOne < userTwo) {
            return new Friendship(userOne, userTwo, status, lastActionUser);
        }
        return new Friendship(userTwo, userOne, status, lastActionUser);
    }

    public List<Long> getFriendIdList(Long userId, ArrayList<Friendship> friendships) {
        ArrayList<Long> ret = new ArrayList<>();
        for (Friendship f : friendships) {
            if (f.getUserOneId() == userId) {
                ret.add(f.getUserTwoId());
            } else {
                ret.add(f.getUserOneId());
            }
        }
        return ret;
    }

    public List<Long> getFriendsIdList(Long userId, ArrayList<Friendship> friendships) {
        List<Long> ret = new ArrayList<>();
        ret.addAll(friendships.stream().filter(u -> u.getUserOneId().equals(userId))
                                .map(Friendship::getUserTwoId).collect(Collectors.toList()));
        ret.addAll(friendships.stream().filter(u -> u.getUserTwoId().equals(userId))
                                .map(Friendship::getUserOneId).collect(Collectors.toList()));
        return ret;
    }

}
