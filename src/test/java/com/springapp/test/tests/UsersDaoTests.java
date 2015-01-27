package com.springapp.test.tests;


import com.springapp.bean.User;
import com.springapp.dao.UsersDao;
import org.junit.Assert;
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

/**
 * Created by Chen on 15-01-23.
 */

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/main/java/com/springapp/configs/dao-context.xml",
        "classpath:com/main/java/com/springapp/configs/security-context.xml",
        "classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoTests {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource datasource;


    private User user = new User("UserFromTest","Test User","hello","test@mun.ca",true,"ROLE_USER");
    private User user2 = new User("UserFromTest2","Test User 2","hello","test@mun.ca",true,"ROLE_USER");
    private User user3 = new User("UserFromTest3","Test User 3","hello","test@mun.ca",true,"ROLE_ADMIN");
    private User user4 = new User("UserFromTest4","Test User 4","hello","test@mun.ca",false,"user");

    @Before //表示run it before the @test
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        jdbcTemplate.execute("delete from offers");//删除table里的信息
        jdbcTemplate.execute("delete from users");//删除table里的信息
    }

    @Test
    public void testCreateRetrieve(){
        usersDao.create(user);

        List<User> userList = usersDao.getAllUsers();
        Assert.assertEquals("One user should be created and retrieved", 1, userList.size());
        Assert.assertEquals("Inserted user should match retrieved", user, userList.get(0));

        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        List<User> userList2 = usersDao.getAllUsers();
        Assert.assertEquals("4 users should be retrieved", 4, userList2.size());
    }

    //TODO - Re-implement this
    @Test
    public void testUsers() {


        usersDao.create(user);

        List<User> userList = usersDao.getAllUsers();

        Assert.assertEquals("Number of users should be 1", 1, userList.size());

        Assert.assertTrue("User should exist", usersDao.exists(user.getUsername()));

        Assert.assertEquals("Created user should be identical to retrieved user", user, userList.get(0));
    }

//    @Test
//    public void getUserList(){
//        List<User> userList = usersDao.getAllUsers();
//
//        Assert.assertEquals("Number of users should be 1", 1, userList.size());
//    }

}
