package com.example.demo.service.impl;

import com.example.demo.domain.entity.Venue;
import com.example.demo.repository.VenueRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class VenueServiceTest {

    VenueService venueService;

    @Mock
    VenueRepository venueRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        venueService = new VenueService(venueRepository);

    }

    @Test
    public void getVenues() throws Exception {

        Venue venue = new Venue();
        List data = new ArrayList();
        data.add(venue);

        when(venueService.findAll()).thenReturn(data);

        List<Venue> list = venueService.findAll();

        assertEquals(list.size(),1);
        verify(venueRepository,times(1)).findAll();
        

    }
}