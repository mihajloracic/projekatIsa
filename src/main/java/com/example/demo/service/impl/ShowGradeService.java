package com.example.demo.service.impl;

import com.example.demo.domain.entity.Show;
import com.example.demo.domain.entity.ShowGrade;
import com.example.demo.repository.ShowGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowGradeService {

    @Autowired
    ShowGradeRepository showGradeRepository;


    public void addGrade(ShowGrade showGrade){

        ShowGrade existing = showGradeRepository.findByUsernameAndShow(showGrade.getUsername(),showGrade.getShow());
        if(existing!= null){
            existing.setGrade(showGrade.getGrade());
            showGradeRepository.save(existing);
        }else{
            showGradeRepository.save(showGrade);
        }

    }

    public double getAverageGrade(Long venueId){
        return showGradeRepository.getShowAverageGrade(venueId);
    }

}
