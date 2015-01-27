package com.springapp.service;

import com.springapp.bean.Offer;
import com.springapp.dao.OffersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Service("offersService")
public class OffersService {
    @Autowired
    private OffersDao offersDao;

    public List<Offer> getCurrent() {
        return offersDao.getOffers();
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public void createOffer(Offer offer) {
        offersDao.create(offer);
//        offersDao.saveOrUpdate(offer);
    }

    public Boolean hasOffers(String username) {
        if (username==null) {
            return false;
        }

        if (offersDao.getOffers(username).size()==0){
            return false;
        }

        return true;
    }

    public Offer getOffer(String username) {
        if (username==null){
            return null;
        }

        List <Offer> offerList = offersDao.getOffers(username);
        if (offerList.size()==0) {
            return null;
        }
        return offerList.get(0);
    }

    public void createOrUpdateOffer(Offer offer) {
        if (offer.getId()!=0){
            offersDao.update(offer);
        } else
            offersDao.create(offer);
    }

    public void delete(int id) {
        offersDao.delete(id);
    }
}
