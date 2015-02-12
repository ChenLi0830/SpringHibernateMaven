package com.springapp.test.tests;

import com.springapp.bean.Message;
import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.MessagesDao;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/main/java/com/springapp/configs/dao-context.xml",
        "classpath:com/main/java/com/springapp/configs/security-context.xml",
        "classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("johnwpurcell", "John Purcell", "hellothere",
            "john@caveofprogramming.com", true, "ROLE_USER");
    private User user2 = new User("richardhannay", "Richard Hannay",
            "the39steps", "richard@caveofprogramming.com", true, "ROLE_ADMIN");
    private User user3 = new User("suetheviolinist", "Sue Black",
            "iloveviolins", "sue@caveofprogramming.com", true, "ROLE_USER");
    private User user4 = new User("rogerblake", "Rog Blake", "liberator",
            "rog@caveofprogramming.com", false, "user");


    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from messages");
        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testDelete() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        Message message = new Message("Test subject 1", "Test content 1", "Wei Wang", "yekiki@gmail.com",user1.getUsername());
        messagesDao.saveOrUpdate(message);
    }

}