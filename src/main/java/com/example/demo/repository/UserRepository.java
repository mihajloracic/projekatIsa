package com.example.demo.repository;


import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameIgnoreCase(String username);

    List<User> findByIdIn(List<Long> ids);

    //kad se dodaju polja u usera

//    List<User> findByIdInOrOrderByFirstname(List<Long> ids);

//    List<User> findByIdInOrOrderByLastname(List<Long> ids);

}
