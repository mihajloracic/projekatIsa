package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void getUsersByIds() {

        List<Long> uIds = new ArrayList<>();
        uIds.add(1L);
        uIds.add(2L);

        List<User> foundUsers = userService.getUsersByIds(uIds);

        assertEquals(foundUsers.size(), 2);
        assertEquals(foundUsers.get(0).getUsername(), "user");

    }
}