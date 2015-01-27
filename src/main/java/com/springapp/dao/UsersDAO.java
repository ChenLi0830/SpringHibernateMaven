package com.springapp.dao;

import com.springapp.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Transactional
@Component("usersDao")
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(User user) {
        Session session = session();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.save(user);
    }

    public boolean exists(String username) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("username",username));
        User user = (User) criteria.uniqueResult();

        return user != null;
        /*MapSqlParameterSource mapSqlParameterSource= new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        return jdbc.queryForObject("select count(*) from users where username=:username", mapSqlParameterSource, Integer.class)>0;*/
    }

    @SuppressWarnings(value = "unchecked")
    public List<User> getAllUsers() {
        return session().createQuery("from User").list();
//        return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
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
