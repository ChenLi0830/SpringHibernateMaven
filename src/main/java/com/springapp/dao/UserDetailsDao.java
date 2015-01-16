package com.springapp.dao;

import com.springapp.bean.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 15-01-12.
 */

@Component
public class UserDetailsDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(UserDetails userDetails) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(userDetails);

        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchekced")
    public List<UserDetails> listUserDetails() {
        Session session = sessionFactory.openSession();
        List<UserDetails> userDetailsList = session.createQuery("from UserDetails").list();
        session.close();
        return userDetailsList;
    }
}
