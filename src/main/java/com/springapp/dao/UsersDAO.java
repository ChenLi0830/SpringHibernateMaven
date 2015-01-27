package com.springapp.dao;

import com.springapp.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public boolean create(User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("username",user.getUsername());
        mapSqlParameterSource.addValue("password",passwordEncoder.encode(user.getPassword()));
        mapSqlParameterSource.addValue("email",user.getEmail());
        mapSqlParameterSource.addValue("enabled",user.isEnabled());
        mapSqlParameterSource.addValue("authority",user.getAuthority());
        mapSqlParameterSource.addValue("name",user.getName());

        return jdbc.update("insert into users (username,password,email,enabled,name,authority) values (:username,:password,:email,:enabled,:name,:authority)", mapSqlParameterSource)==1;
    }

    public boolean exists(String username) {
        MapSqlParameterSource mapSqlParameterSource= new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        return jdbc.queryForObject("select count(*) from users where username=:username", mapSqlParameterSource, Integer.class)>0;
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
