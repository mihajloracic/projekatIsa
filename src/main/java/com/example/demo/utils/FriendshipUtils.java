package com.example.demo.utils;

import com.example.demo.domain.entity.Friendship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FriendshipUtils {

    public Friendship prepareInsert(Long userOne, Long userTwo, int status, Long lastActionUser) {
        if (userOne < userTwo) {
            return new Friendship(userOne, userTwo, status, lastActionUser);
        }
        return new Friendship(userTwo, userOne, status, lastActionUser);
    }

    /*
        vraca listu id-ova koji su u relaciji sa userom na osnovu friendship objekata
     */
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

}
