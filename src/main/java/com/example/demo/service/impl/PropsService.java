package com.example.demo.service.impl;

import com.example.demo.domain.entity.Props;
import com.example.demo.repository.PropsRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public List<Props> getAllProps(){
        return propsRepository.findAll();
    }
    public List<Props> getAllUnAprovedProps(){
        return propsRepository.serchUnApproved();
    }

    public List<Props> getAllAprovedProps(){
        return propsRepository.serchApproved();
    }
}
