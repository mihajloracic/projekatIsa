package com.example.demo.repository;

import com.example.demo.domain.entity.Friendship;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Friendship findByUserOneIdAndUserTwoId(Long userOne, Long userTwo);

    List<Friendship> findByUserOneIdOrUserTwoIdAndStatus(Long user, Long userOne, int status);


}
