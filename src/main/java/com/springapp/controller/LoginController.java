package com.springapp.controller;

import com.springapp.bean.Message;
import com.springapp.bean.User;
import com.springapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 15-01-18.
 */
@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailSender mailSender;

    @RequestMapping("/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping("/logout")
    public String showLogout() {

        return "logout";
    }

    @RequestMapping("/messages")
    public String showMessages() {

        return "messages";
    }

    @RequestMapping("/admin")
    public String showAdmin(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/newaccount")
    public String showNewAccount(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "newaccount";
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(Model model, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "newaccount";
        }

        user.setEnabled(true);
        user.setAuthority("ROLE_USER");

        if (usersService.exists(user.getUsername())) {
            System.out.println("caught username duplicate.");
            bindingResult.rejectValue("username", "DuplicateKey.user.username", "This username already exists.");
            return "newaccount";
        }

        try {
            usersService.createUser(user);
        } catch (DuplicateKeyException ex) {
            bindingResult.rejectValue("username", "DuplicateKey.user.username", "This username already exists.");
            return "newaccount";
        }


        System.out.println(user);
        return "accountcreated";
    }

    @RequestMapping(value = "/getmessages",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getMessages(Principal principal){
        List<Message> messageList = null;
        if (principal == null){
            messageList = new ArrayList<Message>();
        } else {
            String username = principal.getName();
            messageList = usersService.getMessages(username);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("number",messageList.size());
        data.put("messages",messageList);
        return data;
    }

    @RequestMapping(value = "/sendmessage",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String,Object> data){

        Map<String, Object> returnValue = new HashMap<String, Object>();

        String name = (String) data.get("name");
        String email = (String) data.get("email");
        String text = (String) data.get("text");
        Integer target = (Integer) data.get("target");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("lulugeo@gmail.com");
        mail.setTo(email);
        mail.setSubject("Re: " + name + ", your message");
        mail.setText(text);

        try{
            mailSender.send(mail);
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Can't send message");
        }

        returnValue.put("success", true);
        returnValue.put("target", target);

        return returnValue;
    }
}
