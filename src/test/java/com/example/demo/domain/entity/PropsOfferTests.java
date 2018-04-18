package com.example.demo.domain.entity;

import com.example.demo.repository.PropsOfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


public class PropsOfferTests {

    @Mock
    PropsOfferRepository propsOfferRepository;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test() throws Exception {
        assertTrue(true);


    }
}
