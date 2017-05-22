package org.roysez.app.controller;

import org.roysez.app.model.Article;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used for generating home page ;
 * 'GET' : '/home' or '/' ;
 * Contains method {@code getAuthenticatedUserName()} for returning SsoId of authenticated user ;
 *
 * Created by roysez on 02.05.2017.
 * 0:26
 * Package : org.roysez.app.controller
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String accessHomePage(Model model){
        model.addAttribute("authenticatedUserName",getAuthenticatedUserName());
        model.addAttribute("article",new Article());
        return "home";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testPage(Model mode){
        return "testfile";
    }



    protected static String getAuthenticatedUserName(){
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
