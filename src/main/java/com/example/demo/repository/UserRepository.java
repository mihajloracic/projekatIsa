package com.example.demo.repository;


import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameIgnoreCase(String username);

    List<User> findByIdIn(List<Long> ids);

    List<User> findByIdInOrderByFirstnameAsc(List<Long> ids);

    List<User> findByIdInOrderByLastnameAsc(List<Long> ids);

    List<User> findByIdNotIn(List<Long> ids);

    List<User> findDistinctByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);

    List<User> findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(String firstname, String lastname);

    List<User> findByUsernameIn(List<String> usernames);

}
