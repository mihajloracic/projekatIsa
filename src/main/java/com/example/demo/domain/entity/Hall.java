package com.example.demo.domain.entity;


import javax.persistence.*;

@Entity
@Table(name="hall")
public class Hall {

    @Column
    @Id
    private String name;

    @Column(name="number_of_rows")
    private int nRows;

    @Column(name="number_of_seats")
    private int nSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    private Venue venue;

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
