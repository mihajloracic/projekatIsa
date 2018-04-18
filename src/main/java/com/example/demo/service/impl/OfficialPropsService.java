package com.example.demo.service.impl;

import com.example.demo.domain.entity.OfficialProps;
import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.Venue;
import com.example.demo.repository.OfficialPropsRepository;
import com.example.demo.repository.PropsRepository;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfficialPropsService {

    @Autowired
    private OfficialPropsRepository propsRepository;

    @Autowired
    VenueRepository venueRepository;

    public void insertProps(OfficialProps props){
        Venue venue = venueRepository.findOne(props.getVenu().getId());
        props.setVenu(venue);
        propsRepository.save(props);
    }
    public List<OfficialProps> getAllProps(){
        return propsRepository.findAll();
    }
}
