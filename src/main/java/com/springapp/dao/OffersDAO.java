package com.springapp.dao;

import java.util.List;

import com.springapp.bean.Offer;
import com.springapp.bean.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OffersDAO {

	@Autowired
	private SessionFactory sessionFactory;



	public List<Offer> getOffers() {
		return sessionFactory.openSession().createCriteria(Offer.class).list();
	}


	public void saveOrUpdate(Offer offer) {
//		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
//		sessionFactory.getCurrentSession().
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(offer);
		tx.commit();
		session.close();
	}

}
