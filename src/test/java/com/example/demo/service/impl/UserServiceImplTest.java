package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.MatcherUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getUsersByIds() {

        List<Long> uIds = new ArrayList<>();
        uIds.add(1L);
        uIds.add(2L);

        List<User> foundUsers = userService.getUsersByIds(uIds);

        assertEquals(foundUsers.size(), 2);

        assertThat(foundUsers, MatcherUtils.hasUserWithId(1L));
        assertThat(foundUsers, MatcherUtils.hasUserWithId(2L));

        assertThat(foundUsers, MatcherUtils.hasUserWithUsername("user"));

        assertThat(foundUsers, Matchers.not(MatcherUtils.hasUserWithUsername("djuro")));

    }

    @Test
    public void findByFirstnameOrLastname() {
        //user userasd
        //admin admin

        List<User> users = userService.findByFirstnameOrLastname("ic");

        assertEquals(users.size(), 1);

        assertThat(users, MatcherUtils.hasUserWithFirstnameOrLastnameContaining("ic"));
        assertThat(users, MatcherUtils.hasUserWithFirstnameOrLastnameContaining("pet"));


    }

    @Test
    public void findByFirstnameAndLastname() {

        List<User> users = userService.findByFirstnameAndLastname("pet", "ic");

        assertEquals(users.size(), 1);

    }
}