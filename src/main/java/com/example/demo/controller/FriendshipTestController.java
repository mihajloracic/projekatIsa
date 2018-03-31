package com.example.demo.controller;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.model.dto.EditFriendshipDTO;
import com.example.demo.service.FriendshipService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/friend")
public class FriendshipTestController {


    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getFriendships() {
        return ResponseEntity.ok(friendshipService.getFriendships());
    }


    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<?> insertFriendship(@RequestBody EditFriendshipDTO friendshipDTO) {
        Long initiator = userService.findByUsername(friendshipDTO.getInitiatorUsername()).getId();
        Long reciever = userService.findByUsername(friendshipDTO.getRecieverUsername()).getId();
        Friendship f = friendshipService.insertFriendship(initiator, reciever, 0, initiator);
        return ResponseEntity.ok(f);
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ResponseEntity<?> removeFriendship(@RequestBody EditFriendshipDTO friendshipDTO) {
        Long u1 = userService.findByUsername(friendshipDTO.getInitiatorUsername()).getId();
        Long u2 = userService.findByUsername(friendshipDTO.getRecieverUsername()).getId();
        friendshipService.deleteFriendship(u1, u2);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value="/accept", method = RequestMethod.POST)
    public ResponseEntity<?> acceptFriendship(@RequestBody EditFriendshipDTO friendshipDTO) {
        Long initiator = userService.findByUsername(friendshipDTO.getInitiatorUsername()).getId();
        Long reciever = userService.findByUsername(friendshipDTO.getRecieverUsername()).getId();
        try {
            friendshipService.updateFriendship(initiator, reciever, 1, initiator);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value="/decline", method = RequestMethod.POST)
    public ResponseEntity<?> declineFriendship(@RequestBody EditFriendshipDTO friendshipDTO) {
        Long initiator = userService.findByUsername(friendshipDTO.getInitiatorUsername()).getId();
        Long reciever = userService.findByUsername(friendshipDTO.getRecieverUsername()).getId();
        try {
            friendshipService.updateFriendship(initiator, reciever, 2, initiator);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(null);
    }



}
