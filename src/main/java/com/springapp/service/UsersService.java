package com.springapp.service;

import com.springapp.bean.Message;
import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.MessagesDao;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Service("usersService")
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private MessagesDao messagesDao;

    public void createUser(User user) {
        usersDao.create(user);
//        offersDao.saveOrUpdate(offer);
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public void sendMessage(Message message){
        messagesDao.saveOrUpdate(message);
    }

    public User getUser(String username){
        return usersDao.getUser(username);
    }

    public List<Message> getMessages(String username) {
        return messagesDao.getMessages(username);
    }
}
