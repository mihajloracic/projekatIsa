package com.example.demo.model.dto;

import com.example.demo.domain.type.VenueType;

public class VenueTypeNameDTO {

    private VenueType venueType;
    private String name;

    public VenueTypeNameDTO() {
    }

    public VenueType getVenueType() {
        return venueType;
    }

    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
