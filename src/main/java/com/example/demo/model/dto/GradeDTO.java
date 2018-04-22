package com.example.demo.model.dto;

public class GradeDTO {

    private double showGrade;

    private double venueGrade;

    private Long reservationId;

    private String username;


    public GradeDTO() {
    }

    public GradeDTO(double showGrade, double venueGrade, Long reservationId, String username) {
        this.showGrade = showGrade;
        this.venueGrade = venueGrade;
        this.reservationId = reservationId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getShowGrade() {
        return showGrade;
    }

    public void setShowGrade(double showGrade) {
        this.showGrade = showGrade;
    }

    public double getVenueGrade() {
        return venueGrade;
    }

    public void setVenueGrade(double venueGrade) {
        this.venueGrade = venueGrade;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
