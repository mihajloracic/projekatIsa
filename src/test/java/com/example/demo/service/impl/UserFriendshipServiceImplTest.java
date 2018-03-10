package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.service.UserFriendshipService;
import com.example.demo.utils.MatcherUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFriendshipServiceImplTest {


    @Autowired
    UserFriendshipService userFriendshipService;

    @Test
    public void getFriendsFromUser() {
        Long userId = 1L;
        //2 i 4 su mu prijatelji

        List<User> friends = userFriendshipService.getFriendsFromUser(userId);

        assertEquals(friends.size(), 2);

        assertThat(friends, MatcherUtils.hasUserWithId(2L));
        assertThat(friends, MatcherUtils.hasUserWithId(4L));

        List<User> friendsList = userFriendshipService.getFriendsFromUser(38L);
        assertNotNull(friendsList);
        assertEquals(friendsList.size(), 0);
    }
}