package com.example.demo.repository;

import com.example.demo.domain.entity.DiscountEvent;
import com.example.demo.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountEventRepository extends JpaRepository<DiscountEvent,Long>{

    public DiscountEvent findByEvent(Event e);

}
