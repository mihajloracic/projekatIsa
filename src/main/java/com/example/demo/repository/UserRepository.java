package com.example.demo.repository;


import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameIgnoreCase(String username);

    List<User> findByIdIn(List<Long> ids);

    List<User> findByIdInOrderByFirstname(List<Long> ids);

    List<User> findByIdInOrderByLastname(List<Long> ids);

}
