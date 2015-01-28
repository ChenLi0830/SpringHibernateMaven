package com.springapp.dao;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Component("offersDao")
public class OffersDao {

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    @Autowired
    public SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getOffers() {

        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getOffers(String username) {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));
        criteria.add(Restrictions.eq("u.username",username));

        return criteria.list();
    }


    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

    @Transactional
    public void create(List<Offer> offers) {

        for ( int i=0; i<offers.size(); i++ ) {
            session().save(offers.get(i));
            if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session().flush();
                session().clear();
            }
        }
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from offers where id=:id", params) == 1;
    }

    public Offer getOffer(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true and offers.id = :id", params,
                new OfferRowMapper());
    }

}

//@Component
//public class OffersDao {
//
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public List<Offer> getOffers() {
//		return sessionFactory.openSession().createCriteria(Offer.class).list();
//	}
//
//
//	public void saveOrUpdate(Offer offer) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		session.save(offer);
//		tx.commit();
//		session.close();
//	}
//
//	public Offer getOffer(int id){
//		Session session = sessionFactory.openSession();
//		Offer offer = (Offer) session.get(Offer.class,id);
//		session.close();
//		return offer;
//
//	}
//}
