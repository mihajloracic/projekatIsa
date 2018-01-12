package com.example.demo.domain.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "projection")
public class Projection {

    @Column
    private Time time;

    @Id


    @ManyToOne
    private Hall hall;

//    @ManyToOne
//    private Film film;
//
//    @OneToMany
//    private List<Ticket> tickets;

}
