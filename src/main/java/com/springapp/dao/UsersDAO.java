package com.springapp.dao;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("usersDao")
public class UsersDao {

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public boolean create(User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("username",user.getUsername());
        mapSqlParameterSource.addValue("password",passwordEncoder.encode(user.getPassword()));
        mapSqlParameterSource.addValue("email",user.getEmail());
        mapSqlParameterSource.addValue("enabled",user.isEnabled());
        mapSqlParameterSource.addValue("authority",user.getAuthority());

        jdbc.update("insert into users (username,password,email,enabled) values (:username,:password,:email,:enabled)", mapSqlParameterSource);

        return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", mapSqlParameterSource) == 1;
    }

    public boolean exists(String username) {
        MapSqlParameterSource mapSqlParameterSource= new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        return jdbc.queryForObject("select count(*) from users where username=:username", mapSqlParameterSource, Integer.class)>0;
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from users,  authorities where users.username=authorities.username", BeanPropertyRowMapper.newInstance(User.class));
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
