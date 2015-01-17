package com.springapp.controller;

import com.springapp.bean.Offer;
import com.springapp.dao.OffersDAO;
import com.springapp.dao.UserDetailsDao;
import com.springapp.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import javax.validation.Valid;
import java.util.List;

@Controller
public class OffersController {
	@Autowired
	private UserDetailsDao userDetailsDao;
	@Autowired
	private OffersDAO offersDAO;
	@Autowired
	private OffersService offersService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(ModelMap model, @RequestParam("id") String id) {
		System.out.println("Id is:" + id);
		return "home";
	}

	@RequestMapping("/offers")
	public String printWelcome(ModelMap model) {
		List<Offer> offerList= offersService.getCurrent();

		model.addAttribute("message", "Spring dispatcher servlet starts");
		model.addAttribute("offerList", offerList);
		return "offers";
	}


	@RequestMapping("/createoffer")
	public String createOffer(Model model) {
		model.addAttribute("offer",new Offer());
		return "createoffer";
	}

	@RequestMapping(value="/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult bindingResult) {

		if (bindingResult.hasErrors()){
			return "createoffer";
		}

		offersService.createOffer(offer);

		System.out.println(offer);
		return "offercreated";
	}

//	public String showHome(HttpSession httpSession){
////		httpSession.setAttribute("name","SessionChen");
////		UserDetails userDetails = new UserDetails();
////		userDetails.setUserName("WeiWang");
////		userDetailsDao.save(userDetails);
//
//		List<Offer> offerList= offersService.getCurrent();
//		System.out.println(offerList);
//		return "hello";
//	}

//
//	public String showHome(Model model){
//		model.addAttribute("name","<b>Chen</b>");
//		System.out.println("java.class.path = "+System.getProperty("java.class.path"));
//
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//
//		URL[] urls = ((URLClassLoader)cl).getURLs();
//
//		for(URL url: urls){
//			System.out.println(url.getFile());
//		}
//
//		return "hello";
//	}
}