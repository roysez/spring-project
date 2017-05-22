package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.roysez.app.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller used for handling requests such as
 * 'GET' for generating pages: '/signup' , '/login', '/access_denied', '/logout ;
 * 'POST' for register user account: '/signup' ;
 *
 * Created by roysez on 01.05.2017.
 * 23:56
 * Package : org.roysez.app.controller
 */

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String home(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "account/register";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveRegistration( User user,
                                   BindingResult result, Model model) {

        userValidator.validate(user,result);

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "account/register";
        }



        userService.save(user);


        model.addAttribute("user",null);
        model.addAttribute("successfulRegistration", "User " + user.getSsoId() + " has been registered successfully");
        return "account/login";
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        return "account/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/account/login?logout";
    }




    @RequestMapping(value = "/access_denied",method = RequestMethod.GET)
    public String accessDeniedPage(Model model){
        return "account/access_denied";
    }



}
