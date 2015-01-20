package com.springapp.service;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Service("usersService")
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    public void createUser(User user) {
        usersDao.create(user);
//        offersDao.saveOrUpdate(offer);
    }
}
