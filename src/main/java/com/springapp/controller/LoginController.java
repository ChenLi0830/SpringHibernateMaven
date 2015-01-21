package com.springapp.controller;

import com.springapp.bean.User;
import com.springapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Chen on 15-01-18.
 */
@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping("/logout")
    public String showLogout() {

        return "logout";
    }

    @RequestMapping("/newaccount")
    public String showNewAccount(ModelMap modelMap) {
        modelMap.addAttribute("user",new User());
        return "newaccount";
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(Model model, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "newaccount";
        }

        user.setEnabled(true);
        user.setAuthority("user");

        if (usersService.exists(user.getUsername())){
            System.out.println("caught username duplicate.");
            bindingResult.rejectValue("username","DuplicateKey.user.username","This username already exists.");
            return "newaccount";
        }

        try{
            usersService.createUser(user);
        } catch (DuplicateKeyException ex){
            bindingResult.rejectValue("username","DuplicateKey.user.username","This username already exists.");
            return "newaccount";
        }


        System.out.println(user);
        return "accountcreated";
    }
}
