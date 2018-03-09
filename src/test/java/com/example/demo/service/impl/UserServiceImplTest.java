package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void registerUser() {
        List<User> users = userRepository.findAll();
        int count = users.size();

        User user = new User();
        user.setUsername("Zivojin");
        user.setPassword("123");
        userService.registerUser(user);

        int newCount = userRepository.findAll().size();
        assertEquals(newCount, count+1);
    }
}