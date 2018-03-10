package com.example.demo.service.impl;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.FriendshipRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendshipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceImplTest {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    FriendshipRepository friendshipRepository;

    @Test
    public void insertFriendship() {

        Long u1Id = 9L;
        Long u2Id = 8L;

        int count = friendshipRepository.findAll().size();

        friendshipService.insertFriendship(u1Id, u2Id, 0, u1Id);
        assertEquals(friendshipRepository.findAll().size(), count+1);

        //test da je prvi manji dodaj
        Friendship friendship = friendshipRepository.findByUserOneIdAndUserTwoId(u2Id, u1Id);
        assertEquals(friendship.getUserOneId(), u2Id);
        assertEquals(friendship.getUserTwoId(), u1Id);

        assertTrue(friendship.getUserOneId() < friendship.getUserTwoId());

    }

    @Test
    public void deleteFriendship() {

        int count = friendshipRepository.findAll().size();

        Long u1 = 13L;
        Long u2 = 15L;
        Friendship friendship = new Friendship(u1, u2, 0, u1);
        friendshipRepository.save(friendship);

        assertEquals(friendshipRepository.findAll().size(), count+1);

        friendshipService.deleteFriendship(u1, u2);

        assertEquals(friendshipRepository.findAll().size(), count);
    }
}