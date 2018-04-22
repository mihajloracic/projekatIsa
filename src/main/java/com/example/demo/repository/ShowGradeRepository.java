package com.example.demo.repository;

import com.example.demo.domain.entity.Show;
import com.example.demo.domain.entity.ShowGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowGradeRepository extends JpaRepository<ShowGrade,Long>{

    public ShowGrade findByUsernameAndShow(String username,Show show);

    @Query("SELECT coalesce(AVG(grade),0) FROM ShowGrade WHERE id = :id")
    public double getShowAverageGrade(@Param("id") Long id);
}
