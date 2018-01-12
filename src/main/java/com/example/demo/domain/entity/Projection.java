package com.example.demo.domain.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "projection")
public class Projection {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private Film film;
//
//    @OneToMany
//    private List<Ticket> tickets;

}
