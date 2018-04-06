package com.example.demo.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="hall")
public class Hall {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name="number_of_rows")
    private int nRows;

    @Column(name="number_of_seats")
    private int nSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    private Venue venue;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private List<Event> events;

    public Hall() {
    }

    public Hall(String name, int nRows, int nSeats) {
        this.name = name;
        this.nRows = nRows;
        this.nSeats = nSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnRows() {
        return nRows;
    }

    public void setnRows(int nRows) {
        this.nRows = nRows;
    }

    public int getnSeats() {
        return nSeats;
    }

    public void setnSeats(int nSeats) {
        this.nSeats = nSeats;
    }
}
