package com.example.demo.service.impl;

import com.example.demo.domain.entity.User;
import com.example.demo.model.dto.ChangePasswordDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findByUsernameIgnoreCase(changePasswordDTO.getUsername());

        String oldPassword = passwordEncoder.encode(changePasswordDTO.getOldPassword());
        String newPassword = passwordEncoder.encode(user.getPassword());
        return false;
    }
}
