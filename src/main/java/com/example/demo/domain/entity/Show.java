package com.example.demo.domain.entity;


import com.example.demo.domain.type.ShowType;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Show {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private ShowType showType;

    @Column(name="actors",columnDefinition = "text")
    private String actors;

    @Column(name="genre")
    private String genre;

    @Column(name="description",columnDefinition = "text")
    private String description;

    @Column(name="length")
    private int length;

    @Column(name="grade")
    private double grade;

    public Show() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

}
