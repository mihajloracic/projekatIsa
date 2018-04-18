package com.example.demo.service.impl;

import com.example.demo.domain.entity.PropsOffer;
import com.example.demo.domain.entity.User;
import com.example.demo.model.dto.PropsOfferDTO;
import com.example.demo.repository.PropsOfferRepository;
import com.example.demo.repository.PropsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropsOfferService {

    @Autowired
    PropsOfferRepository propsOfferRepository;

    @Autowired
    PropsRepository propsRepository;

    @Autowired
    NotificationService notificationService;

    public void addUpdateOffer(PropsOfferDTO propsOfferDTO){
        PropsOffer propsOffer = new PropsOffer(propsRepository.findOne(propsOfferDTO.getPropsId()),propsOfferDTO.getUser(),propsOfferDTO.getOfferValue());
        if(propsOfferRepository.serchIfOfferMade(propsOffer.getUser(),propsOffer.getProps()).size() > 0){
            propsOffer = propsOfferRepository.serchIfOfferMade(propsOffer.getUser(),propsOffer.getProps()).get(0);
            propsOffer.setValue(propsOfferDTO.getOfferValue());
        }
        propsOfferRepository.save(propsOffer);
    }
    public List<PropsOffer> getAllOffers(){
        return propsOfferRepository.findAll();
    }

    public List<PropsOffer> getOffersByProps(long id)
    {
        return propsOfferRepository.getOffersByProps(propsRepository.findOne(id));
    }

    public List<PropsOffer> getMyOffers(User loggedInUser) {
        return propsOfferRepository.serchOffersByUser(loggedInUser);
    }

    public void verfyValue(PropsOfferDTO propsOfferDTO) throws NotFoundException {
        PropsOffer propsOffer = propsOfferRepository.findOne(propsOfferDTO.getPropsId());
        if(propsOffer.getValue() != propsOfferDTO.getOfferValue()){
            throw new NotFoundException("User changed offer. Please try again.");
        }
    }

    public void sellItem(PropsOfferDTO propsOfferDTO) {
        PropsOffer propsOffer = propsOfferRepository.findOne(propsOfferDTO.getPropsId());
        propsOffer.getProps().setSold(true);
        notificationService.notifyNotAcceptedOffer(propsOfferRepository.getNotAcceptedProps(propsOffer.getProps(),propsOffer.getUser()));
        notificationService.notifyAcceptedOffer(propsOffer);
        propsOfferRepository.save(propsOffer);
    }
}
