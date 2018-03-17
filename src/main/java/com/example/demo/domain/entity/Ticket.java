package com.example.demo.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="row")
    private int row;

    @Column(name="seat")
    private int seat;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
