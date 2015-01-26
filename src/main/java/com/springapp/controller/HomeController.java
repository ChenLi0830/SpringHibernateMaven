package com.springapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 1/16/15.
 */
@Controller

public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/")
	public String showHome(Model model){
		return "home";
	}

}
