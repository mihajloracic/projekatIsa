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

    @Column(name="number_of_cols")
    private int nCols;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Venue venue;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private List<Seat> seats;

    public Hall() {
    }

    public Hall(String name, int nRows, int nCols) {
        this.name = name;
        this.nRows = nRows;
        this.nCols = nCols;
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

    public int getnCols() {
        return nCols;
    }

    public void setnCols(int nCols) {
        this.nCols = nCols;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
