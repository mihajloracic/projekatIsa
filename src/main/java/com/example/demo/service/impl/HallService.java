package com.example.demo.service.impl;

import com.example.demo.domain.entity.Hall;
import com.example.demo.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {


    @Autowired
    HallRepository hallRepository;

    public List<Hall> getAll(){
        return hallRepository.findAll();
    }

    public void addHall(Hall h){
        hallRepository.save(h);
    }

    public Hall findById(Long id){
        return hallRepository.findOne(id);
    }

}
