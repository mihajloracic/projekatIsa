package com.example.demo.model.dto;

import com.example.demo.domain.entity.User;

public class PropsOfferDTO {

    User user;
    long propsId;
    double offerValue;
    public PropsOfferDTO(){

    }
    public PropsOfferDTO(long propsId, double offerValue) {

        this.propsId = propsId;
        this.offerValue = offerValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPropsId() {
        return propsId;
    }

    public void setPropsId(long propsId) {
        this.propsId = propsId;
    }

    public double getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(double offerValue) {
        this.offerValue = offerValue;
    }
}
