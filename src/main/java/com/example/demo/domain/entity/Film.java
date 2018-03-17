package com.example.demo.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="actors",columnDefinition = "text")
    private String actors;

    @Column(name="genre")
    private String genre;

    @Column(name="description",columnDefinition = "text")
    private String description;

    @Column(name="lenght")
    private int length;

    @Column(name="grade")
    private float grade;

    @Column
    private float price;



}
