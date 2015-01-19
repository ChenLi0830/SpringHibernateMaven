package com.springapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.springapp.bean.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component("offersDao")
public class OffersDAO {

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Offer> getOffers() {
        return jdbc.query("select * from offers ", new RowMapper<Offer>() {
            @Override
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setText(rs.getString("text"));

                return offer;
            }
        });
    }

    public boolean update(Offer offer){
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("UPDATE offers SET name=:name,text=:text,email=:email where id=:id",beanPropertySqlParameterSource)==1;
    }

    public boolean create (Offer offer){
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(offer);
        return jdbc.update("insert into offers (id,name,text,email) values (:id,:name,:text,:email)",beanPropertySqlParameterSource)==1;
    }


    @Transactional
    public int[] create(List<Offer> offers) {

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());

        return jdbc.batchUpdate("insert into offers (id, name, text, email) values (:id, :name, :text, :email)", params);
    }

    public boolean delete(int id){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        return jdbc.update("delete from offers where id=:id",mapSqlParameterSource) == 1;
    }

    public Offer getOffer(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        return jdbc.queryForObject("select * from offers where id=:id", mapSqlParameterSource, new RowMapper<Offer>() {
            @Override
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setText(rs.getString("text"));

                return offer;
            }
        });
    }
}

//@Component
//public class OffersDAO {
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
