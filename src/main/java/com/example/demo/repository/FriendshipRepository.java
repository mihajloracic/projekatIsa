package com.example.demo.repository;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Friendship findByUserOneIdAndUserTwoId(Long userOne, Long userTwo);

    List<Friendship> findByUserOneIdOrUserTwoId(Long userOne, Long userTwo);

    @Query(value = "SELECT * FROM friendship \n" +
            "WHERE (user_one_id = :userOne OR user_two_id = :userTwo) AND status = :status", nativeQuery = true)
    List<Friendship> findFriendshipsFromUser(
            @Param("userOne") Long userOne,
            @Param("userTwo") Long userTwo,
            @Param("status") int status
    );

    @Query(value = "SELECT * FROM friendship \n" +
            "WHERE (user_one_id = :userOne OR user_two_id = :userTwo) AND status = :status AND action_user_id != :actionUser", nativeQuery = true)
    List<Friendship> findFriendshipsStatusPending(
            @Param("userOne") Long userOne,
            @Param("userTwo") Long userTwo,
            @Param("status") int status,
            @Param("actionUser") Long actionUser
    );


    @Transactional
    @Modifying
    @Query(value = "UPDATE friendship\n" +
            "SET status = :status, action_user_id = :actionUser\n" +
            "WHERE (user_one_id = :userOne AND user_two_id = :userTwo)", nativeQuery = true)
    void updateFriendship(
            @Param("userOne") Long userOne,
            @Param("userTwo") Long userTwo,
            @Param("status") int status,
            @Param("actionUser") Long actionUser
    );

}
