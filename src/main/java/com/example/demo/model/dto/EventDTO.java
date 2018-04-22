package com.example.demo.model.dto;

import java.sql.Date;
import java.sql.Time;

public class EventDTO {

    private Date date;
    private Time time;
    private double price;
    private Long hallId;
    private Long showId;

    public EventDTO() {
    }

    public EventDTO(Date date, Time time, double price, Long hallId, Long showId) {
        this.date = date;
        this.time = time;
        this.price = price;
        this.hallId = hallId;
        this.showId = showId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
