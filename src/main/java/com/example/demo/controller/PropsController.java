package com.example.demo.controller;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import com.example.demo.service.impl.PropsService;
import com.example.demo.service.impl.StorageService;
import com.example.demo.service.impl.UserGetterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;


@RestController
@RequestMapping("/props")
public class PropsController {

    @Autowired
    PropsService propsService;

    @Autowired
    UserGetterService userGetterService;

    @Autowired
    StorageService storageService;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProps(HttpServletRequest request, @RequestBody Props props) throws NotFoundException {
        props.setUserCreated(userGetterService.getLoggedInUser(request));
        //props.setImageUrl(storageService.store(file));
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
    public ResponseEntity<?> getAllApprovedProps(@RequestParam("cinemaId") long id) {
        return ResponseEntity.ok(propsService.getAllAprovedProps(id));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/propsbyid")
    public ResponseEntity<?> getPropsById(@RequestParam("propsId") long id) {
        return ResponseEntity.ok(propsService.findPropsById(id));
    }


    @RequestMapping(method = RequestMethod.GET,value = "/myProps")
    public ResponseEntity<?> getMyProps(HttpServletRequest request) throws NotFoundException {
        User user = userGetterService.getLoggedInUser(request);
        return ResponseEntity.ok(propsService.getMyProps(user));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/admin")
    public ResponseEntity<?> getAllUnApprovedProps(@RequestParam("cinemaId") long id) {
        //TODO dodati za admina proveru(samo on moze da vidi ovo
        return ResponseEntity.ok(propsService.getAllUnAprovedProps(id));
    }
    //TODO pre-authorize admin
    @RequestMapping(method = RequestMethod.DELETE,value = "/admin")
    public ResponseEntity<?> deleteProps(HttpServletRequest request,@RequestBody Props props) throws NotFoundException {
        propsService.deleteProps(props);
        return ResponseEntity.ok("delete sucess");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/admin")
    public ResponseEntity<?> aproveProps(HttpServletRequest request,@RequestBody Props props) throws NotFoundException {
        userGetterService.getLoggedInUser(request);
        props = propsService.updateProps(props);
        return ResponseEntity.ok(props);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/image")
    public ResponseEntity<?> setImage(HttpServletRequest request,@RequestParam("file")MultipartFile file,@RequestParam Long id) throws NotFoundException {
        Props props = propsService.findPropsById(id);
        props.setImageUrl(storageService.store(file));
        propsService.insertProps(props);
        return ResponseEntity.ok(props);
    }


}
