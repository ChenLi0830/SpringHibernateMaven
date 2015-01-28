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
import java.util.List;

//import org.junit.Assert.assert.*;

/**
 * Created by Chen on 15-01-23.
 */

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/main/java/com/springapp/configs/dao-context.xml",
		"classpath:com/main/java/com/springapp/configs/security-context.xml",
		"classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests2 {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateUser() {

		User user = new User("johnwpurcell", "John Purcell", "hellothere",
				"john@caveofprogramming.com", true, "user");

		usersDao.create(user);

		Offer offer = new Offer(user, "This is a test offer.");

		offersDao.create(offer);

		List<Offer> offers = offersDao.getOffers();

		Assert.assertEquals("Should be one offer in database.", 1, offers.size());

		Assert.assertEquals("Retrieved offer should match created offer.", offer,
				offers.get(0));

		// Get the offer with ID filled in.
		offer = offers.get(0);

		offer.setText("Updated offer text.");
		Assert.assertTrue("Offer update should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());

		Assert.assertEquals("Updated offer should match retrieved updated offer",
				offer, updated);

		offersDao.delete(offer.getId());

		List<Offer> empty = offersDao.getOffers();

		Assert.assertEquals("Offers lists should be empty.", 0, empty.size());
	}

}
