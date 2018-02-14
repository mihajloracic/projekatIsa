package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.entity.VerificationToken;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

    @Autowired
    VerificationTokenRepository tokenRepository;


    @Override
    public void createVerificationToken(User user, String token) {
        tokenRepository.save(new VerificationToken(token, user));
    }

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUser(User user) {
        return tokenRepository.findByUser(user);
    }
}
