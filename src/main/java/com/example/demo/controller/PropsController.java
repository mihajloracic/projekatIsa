package com.example.demo.controller;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import com.example.demo.service.impl.PropsService;
import com.example.demo.service.impl.UserGetterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/props")
public class PropsController {

    @Autowired
    PropsService propsService;

    @Autowired
    UserGetterService userGetterService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps(HttpServletRequest request,@RequestBody Props props) throws NotFoundException {
        props.setUserCreated(userGetterService.getLoggedInUser(request));
        propsService.insertProps(props);
        return ResponseEntity.ok(props);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updaterops(HttpServletRequest request,@RequestBody Props props) throws NotFoundException {
        User u = userGetterService.getLoggedInUser(request);
        //TODO provera da li je taj user kreirao entite koji mennja
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
    public ResponseEntity<?> aproveProps(HttpServletRequest request,@RequestBody Props props) throws NotFoundException {
        userGetterService.getLoggedInUser(request);
        props = propsService.updateProps(props);
        return ResponseEntity.ok(props);
    }

}
