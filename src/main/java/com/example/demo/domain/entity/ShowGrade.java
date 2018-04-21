package com.example.demo.domain.entity;

import javax.persistence.*;

@Entity
public class ShowGrade {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private double grade;

    @ManyToOne
    private Show show;
}
