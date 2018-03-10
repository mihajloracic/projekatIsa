package com.example.demo.controller;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendshipTestController {



    @Autowired
    FriendshipService friendshipService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getFriendships() {
        return ResponseEntity.ok(friendshipService.getFriendships());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insertFriendship(@RequestBody Friendship friendship) {
        Long u1 = friendship.getUserOneId();
        Long u2 = friendship.getUserTwoId();
        friendshipService.insertFriendship(u1, u2, 0, u2);
        return ResponseEntity.ok(friendship);
    }
}
