package com.example.demo.model.dto;

public class VenueShowDTO {

    private Long venueId;
    private Long showId;

    public VenueShowDTO() { }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
