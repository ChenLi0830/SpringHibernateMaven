package com.springapp.test.tests;


import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
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
import java.util.ArrayList;
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
public class OffersDaoTests {

    @Autowired
    @SuppressWarnings("unchecked")
    private DataSource datasource;

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private UsersDao usersDao;

    @Before //表示run it before the @test
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        jdbcTemplate.execute("delete from offers");//删除table里的信息
        jdbcTemplate.execute("delete from users");//删除table里的信息
    }

    @Test
    public void testOffers() {
        User user = new User("UserFromTest","Test User","hello","test@mun.ca",true,"ROLE_USER");
        Assert.assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "Best UI designer in town. (0|-)");
        Offer offer2 = new Offer(user, "Best backend developer in town. :P");
        Assert.assertTrue("Offer should be created successfully", offersDao.create(offer));

        List<Offer> offerList = offersDao.getOffers();
        Assert.assertEquals("There should be one offer", 1, offerList.size());

        Assert.assertEquals("The created offer should be identical to the retrieved offer", offer, offerList.get(0));

        List<Offer> offerList2 = new ArrayList<Offer>();
        offerList2.add(offer);
        offerList2.add(offer2);

        offersDao.create(offerList2);
        Assert.assertEquals("There should be 3 offers.", 3, offersDao.getOffers().size());


        /*Test by id;*/
        offer = offerList.get(0);
        offer.setText("Hey! I am the best UI designer in town. (0|-)");
        offersDao.update(offer);
        System.out.println(offer.getId());
        Offer updatedOffer = offersDao.getOffer(offer.getId());
        Assert.assertEquals("Updated offer should match retrieved updated offer", offer, updatedOffer);


        List <Offer> userList = offersDao.getOffers(user.getUsername());
        Assert.assertEquals("There should be 3 offers", 3, userList.size());

        /*Test deletion*/
        Assert.assertTrue("Should delete the offer successfully.",offersDao.delete(offer.getId()));
        Assert.assertEquals("There should be 2 offers after the deletion", 2, offersDao.getOffers().size());
    }

//    @Test
//    public void getUserList(){
//        List<User> userList = usersDao.getAllUsers();
//
//        Assert.assertEquals("Number of users should be 1", 1, userList.size());
//    }

}
