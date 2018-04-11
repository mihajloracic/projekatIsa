package com.example.demo.controller;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import com.example.demo.model.dto.EditFriendshipDTO;
import com.example.demo.model.dto.FindUserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserFriendshipService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friends")
public class UserFriendshipController {

    @Autowired
    UserFriendshipService userFriendshipService;


    @Autowired
    UserService userService;

    @RequestMapping(value = "/edit/add", method = RequestMethod.POST)
    public ResponseEntity<?> getUsersByName(@RequestBody FindUserDTO fu) { //@RequestBody   //dto neki napraviti za id ulogovanog i ime
        User u = userService.findByUsername(fu.getUsername());
        List<User> users = (fu.isFriends()) ? userFriendshipService.getFriendsFromUser(u.getId()) :
                userFriendshipService.getNonFriendsFromUser(u.getId());
        String queryParam = fu.getQueryParam().toLowerCase();
        List<User> ret = userFriendshipService.searchUsers(users, queryParam);

        return ResponseEntity.ok(ret);
    }

    @RequestMapping(value="/getFriendRequests/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getFriendRequests(@PathVariable("username") String username) {
        Long u = userService.findByUsername(username).getId();
        List<User> friendRequests = userFriendshipService.getUserFriendRequests(u);

        return ResponseEntity.ok(friendRequests);
    }

    @RequestMapping(value="/getFriendsOrderedByFirstname/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getFriendsByFirstname(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userFriendshipService.getFriendsFromUserOrderedByFirstname(user.getId()));
    }

    @RequestMapping(value="/getFriendsOrderedByLastname/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getFriendsByLastname(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userFriendshipService.getFriendsFromUserOrderedByLastname(user.getId()));
    }

}
