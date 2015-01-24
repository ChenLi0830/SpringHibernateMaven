package com.springapp.test.tests;


import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen on 15-01-23.
 */

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/main/java/com/springapp/configs/dao-context.xml",
        "classpath:com/main/java/com/springapp/configs/security-context.xml",
        "classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

        @Test
        public void testCreateUser(){
                Assert.assertEquals("dummy test ", 1, 1);
        }

}
