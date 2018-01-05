package com.example.demo.controller;

import com.example.demo.domain.entity.OfficialProps;
import com.example.demo.model.security.CerberusUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.OfficialPropsService;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/officialprops")
public class OfficialPropsController {

    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    OfficialPropsService officialPropsService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps(HttpServletRequest request, @RequestBody OfficialProps props) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        if(user.getAuthorities().contains(null)){//TODO proveriti da li je user admin

        }
        props.setUserCreated(userRepository.findByUsernameIgnoreCase(username));

        officialPropsService.insertProps(props);
        return ResponseEntity.ok(props);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllProps() {
        return ResponseEntity.ok(officialPropsService.getAllProps());
    }

}
