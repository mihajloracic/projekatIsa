package com.example.demo.domain.entity;


import javax.persistence.*;

@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
