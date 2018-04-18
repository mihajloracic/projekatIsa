package com.example.demo.service.impl;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.PropsRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PropsService {

    @Autowired
    private PropsRepository propsRepository;

    public void insertProps(Props props){
        props.setApproved(false);
        propsRepository.save(props);
    }
    public Props updateProps(Props props){
        Props propsToSave = propsRepository.findOne(props.getId());
        propsToSave.setApproved(true);
        propsRepository.save(propsToSave);
        return propsToSave;
    }
    public void deleteProps(Props props){
        propsRepository.delete(props);
    }

    public Props findPropsById(Long id){
        return propsRepository.findOne(id);
    }
    public List<Props> getAllProps(){
        return propsRepository.findAll();
    }
    public List<Props> getMyProps(User user){

        List<Props> props = propsRepository.findAll();

        List<Props> listToReturn = new ArrayList<>();
        for(Props p : props){
            if (p.getUserCreated().getId() == user.getId()){
                listToReturn.add(p);
            }
        }
        return listToReturn;
    }

    public List<Props> getAllUnAprovedProps(Long cinemaId){

        return propsRepository.serchUnApproved(cinemaId);
    }

    public List<Props> getAllAprovedProps(Long cinemaId){

        return propsRepository.serchApproved(cinemaId, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
    }
}
