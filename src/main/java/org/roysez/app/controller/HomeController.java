package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by roysez on 02.05.2017.
 * 0:26
 * Package : org.roysez.app.controller
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String accessHomePage(Model model){
        List<User> listOfAllUsers = userService.findAll();
        model.addAttribute("listOfAllUsers",listOfAllUsers);
        model.addAttribute("authenticatedUserName",getAuthenticatedUserName());
        return "home";
    }

     private String getAuthenticatedUserName(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}