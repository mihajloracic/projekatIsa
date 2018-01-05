package com.example.demo.security;

import com.example.demo.domain.entity.Props;
import com.example.demo.repository.PropsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropsService {

    @Autowired
    private PropsRepository propsRepository;

    public void insertProps(Props props){
        propsRepository.save(props);
    }
    public List<Props> getAllProps(){
        return propsRepository.findAll();
    }
}
