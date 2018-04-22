package com.example.demo.model.dto;

public class DiscountDTO {

    private double price;

    private Long eventId;

    public DiscountDTO() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
