package com.example.demo.service.impl;

import com.example.demo.domain.entity.DiscountEvent;
import com.example.demo.domain.entity.Event;
import com.example.demo.repository.DiscountEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountEventService {

    @Autowired
    DiscountEventRepository discountEventRepository;

    public Event getEventById(Long id){
        return  discountEventRepository.findOne(id).getEvent();
    }

    public void addDiscount(DiscountEvent de) {
        discountEventRepository.save(de);
    }

    public List<DiscountEvent> getAll(){
        return discountEventRepository.findAll();
    }


}
