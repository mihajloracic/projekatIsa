package com.example.demo.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.entity.VerificationToken;

public interface VerificationTokenService {

    void createVerificationToken(User user, String token);

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
