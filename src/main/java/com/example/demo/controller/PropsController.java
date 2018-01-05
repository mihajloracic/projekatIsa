package com.example.demo.controller;

import com.example.demo.domain.entity.Props;
import com.example.demo.model.security.CerberusUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.PropsService;
import com.example.demo.security.TokenUtils;
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
@RequestMapping("/props")
public class PropsController {

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps(HttpServletRequest request,@RequestBody Props props) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        props.setUserCreated(userRepository.findByUsernameIgnoreCase(username));
        propsService.insertProps(props);
        return ResponseEntity.ok(props);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updaterops(HttpServletRequest request,@RequestBody Props props) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        if(props.getUserCreated().getId() != userRepository.findByUsernameIgnoreCase(username).getId()){//if this is the user that created that post
            return ResponseEntity.badRequest().body(null);
        }
        propsService.insertProps(props);
        return ResponseEntity.ok(props);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllApprovedProps() {
        return ResponseEntity.ok(propsService.getAllAprovedProps());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/admin")
    public ResponseEntity<?> getAllUnApprovedProps() {
        //TODO dodati za admina proveru(samo on moze da vidi ovo
        return ResponseEntity.ok(propsService.getAllUnAprovedProps());
    }
    @RequestMapping(method = RequestMethod.POST,value = "/admin")
    public ResponseEntity<?> aproveProps(HttpServletRequest request,@RequestBody Props props) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        //TODO check if user is admin
        props = propsService.updateProps(props);
        return ResponseEntity.ok(props);
    }

}
