package com.example.demo.domain.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User reservationOwner;

    @ManyToOne
    private Event event;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<ReservationSeat> reservationSeats;

    public Reservation() {
    }

    public Reservation(User reservationOwner, Event event) {
        this.reservationOwner = reservationOwner;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReservationOwner() {
        return reservationOwner;
    }

    public void setReservationOwner(User reservationOwner) {
        this.reservationOwner = reservationOwner;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
