package com.example.demo.service.impl;

import com.example.demo.domain.entity.Notification;
import com.example.demo.domain.entity.PropsOffer;
import com.example.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void notifyNotAcceptedOffer(PropsOffer propsOffer){
        Notification notification = new Notification("You offer for " + propsOffer.getProps().getName() + " has not been accepted", propsOffer.getUser());
        notificationRepository.save(notification);
    }
    public void notifyNotAcceptedOffer(List<PropsOffer> propsOffers){
        for(PropsOffer p : propsOffers){
            notifyNotAcceptedOffer(p);
        }
    }

    public void notifyAcceptedOffer(PropsOffer propsOffer){
        Notification notification = new Notification("You offer for " + propsOffer.getProps().getName() + " has been accepted", propsOffer.getUser());
        notificationRepository.save(notification);
    }
}
