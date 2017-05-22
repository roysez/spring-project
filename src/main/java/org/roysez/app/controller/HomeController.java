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
 * Controller used for handling requests :
 * 'GET' : '/home' or '/' ;
 * Contains static method {@code getAuthenticatedUserName()} for returning SsoId of authenticated user ;
 *
 * Created by roysez on 02.05.2017.
 * 0:26
 * Package : org.roysez.app.controller
 */
@Controller
public class HomeController {

    /**
     * Autowire by the implementation of {@link UserService},
     * defined in the Spring Container ;
     */
    @Autowired
    private UserService userService;


    /**
     * Redirects to JSP, which generates home page, with given attributes;
     * @param model ;
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String accessHomePage(Model model){
        model.addAttribute("authenticatedUserName",getAuthenticatedUserName());
        model.addAttribute("article",new Article());
        return "home";
    }


    /**
     * Static method  for returning SsoId of authenticated user ;
     * @return unique userName;
     */
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
