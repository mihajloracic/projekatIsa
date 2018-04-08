package com.example.demo.model.dto;

import java.util.List;

public class ReservationDTO {

    private Long eventId;
    private String username;
    private List<ReservationSeatDTO> seats;
    private List<String> invitedUsernames;

    public ReservationDTO() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ReservationSeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<ReservationSeatDTO> seats) {
        this.seats = seats;
    }

    public List<String> getInvitedUsernames() {
        return invitedUsernames;
    }

    public void setInvitedUsernames(List<String> invitedUsernames) {
        this.invitedUsernames = invitedUsernames;
    }
}
