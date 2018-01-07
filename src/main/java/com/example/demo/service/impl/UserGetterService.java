package com.example.demo.service.impl;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import com.example.demo.model.security.CerberusUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserGetterService {
    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    PropsService propsService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    public User getLoggedInUser(HttpServletRequest request) throws NotFoundException {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
        } else {
            throw new NotFoundException("Wrong email/password!");
        }
        return userRepository.findByUsernameIgnoreCase(username);
    }
}
