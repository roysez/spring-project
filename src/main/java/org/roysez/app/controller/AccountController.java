package org.roysez.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by roysez on 01.05.2017.
 * 23:56
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {


    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String home(Model model){

        return "account/register";
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){

        return "account/login";
    }


    @RequestMapping(value = "/access_denied",method = RequestMethod.GET)
    public String accessDeniedPage(Model model){

        model.addAttribute("user",getAuthenticatedUserName());
        return "account/access_denied";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/account/login?logout";
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
