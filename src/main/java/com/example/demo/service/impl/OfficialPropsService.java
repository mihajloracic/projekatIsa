package com.example.demo.service.impl;

import com.example.demo.domain.entity.OfficialProps;
import com.example.demo.domain.entity.Props;
import com.example.demo.repository.OfficialPropsRepository;
import com.example.demo.repository.PropsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfficialPropsService {
    @Autowired
    private OfficialPropsRepository propsRepository;

    public void insertProps(OfficialProps props){
        propsRepository.save(props);
    }
    public List<OfficialProps> getAllProps(){
        return propsRepository.findAll();
    }
}
