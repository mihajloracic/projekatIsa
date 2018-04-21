package com.example.demo.domain.entity;

import com.example.demo.domain.type.EventType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType eventType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Hall hall;

    private double price;

    @Column(name = "time")
    private Time time;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    private Show show;

    @ManyToOne
    private Venue venue;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")   //event ne vidi
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")   //event ne vidi
    private List<ReservationSeat> seats;

    //price

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


}
