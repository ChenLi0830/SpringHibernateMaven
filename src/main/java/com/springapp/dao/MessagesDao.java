package com.springapp.dao;

import com.springapp.bean.Message;
import com.springapp.bean.Offer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component("messagesDao")
public class MessagesDao {

    @Autowired
    public SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Message> getMessages() {

        Criteria criteria = session().createCriteria(Message.class);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Message> getMessages(String username) {
        Criteria criteria = session().createCriteria(Message.class);
        criteria.add(Restrictions.eq("username",username));
        return criteria.list();
    }


    public void saveOrUpdate(Message message) {
        System.out.println(message);
        session().saveOrUpdate(message);
    }

    @Transactional
    public void create(List<Message> messages) {

        for ( int i=0; i<messages.size(); i++ ) {
            session().save(messages.get(i));
            if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session().flush();
                session().clear();
            }
        }
    }

    public boolean delete(int id) {
        Query query = session().createQuery("delete Message where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate()==1;//executeUpdate returns numbers of rows effected.
    }

    public Message getMessage(int id) {
        Criteria criteria = session().createCriteria(Message.class);
        criteria.add(Restrictions.eq("id",id));
        return (Message) criteria.uniqueResult();
    }

}
