package com.springapp.service;

import com.springapp.bean.Offer;
import com.springapp.dao.OffersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Service("offersService")
public class OffersService {
    @Autowired
    private OffersDAO offersDAO;

    public List<Offer> getCurrent() {
        return offersDAO.getOffers();
    }

    public void createOffer(Offer offer) {
        //TODO offersDAO.saveOrUpdate(offer);
    }
}
