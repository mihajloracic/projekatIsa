package com.example.demo.model.dto;

public class DiscountReservationDTO {

    private Long discountEventId;
    private String username;

    public DiscountReservationDTO() {
    }

    public Long getDiscountEventId() {
        return discountEventId;
    }

    public void setDiscountEventId(Long discountEventId) {
        this.discountEventId = discountEventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
