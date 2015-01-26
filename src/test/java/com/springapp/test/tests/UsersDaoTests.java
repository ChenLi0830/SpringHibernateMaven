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


    @Before //表示run it before the @test
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        jdbcTemplate.execute("delete from offers");//删除table里的信息
        jdbcTemplate.execute("delete from users");//删除table里的信息
    }


    @Test
    public void testCreateUser() {
        User user = new User("UserFromTest","Test User","hello","test@mun.ca",true,"ROLE_USER");
        Assert.assertTrue("User creation should return true",usersDao.create(user));

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
