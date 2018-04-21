package com.example.demo.controller;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.model.dto.ChangePasswordDTO;
import com.example.demo.model.dto.EditFriendshipDTO;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UserProfile;

import javax.xml.ws.soap.Addressing;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value="/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> editPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        userProfileService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(null);
    }

}
