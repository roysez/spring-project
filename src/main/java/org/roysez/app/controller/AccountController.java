package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller used to handle requests coming from the client by the url '/account/*' ;
 * Invokes a business class to process business-related tasks ;
 *
 * 'GET' redirect the client to a logical view:  '/signup' , '/login', '/access_denied', '/logout ;
 * 'POST' : '/signup' ;
 *
 * Created by roysez on 01.05.2017.
 * 23:56
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    /**
     * Autowire by the implementation of {@link UserService},
     * defined in the Spring Container ;
     */
    @Autowired
    private UserService userService;

    /**
     * Autowire by the implementation of {@link Validator},
     * defined in the Spring Container ;
     */
    @Autowired
    private Validator userValidator;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Method redirects to a specific view with register form;
     * @param model ;
     * @return redirects to JSP  ;
     */
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String home(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "account/register";
    }

    /**
     * Used for registration of user, handle request with:
     * @param user - entity to persist ;
     * @param result - handling validation errors, if exist ;
     * @param model ;
     * @return redirects to a specific view ;
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveRegistration( User user,
                                   BindingResult result, Model model) {

        userValidator.validate(user,result);

        if (result.hasErrors()) {
            logger.warn("There are error (invalid data)");
            return "account/register";
        }

        userService.save(user);

        model.addAttribute("user",null);
        model.addAttribute("successfulRegistration", "User " + user.getSsoId() + " has been registered successfully");
        return "account/login";
    }


    /**
     * Method redirects to a specific view with Login form;
     * @param model ;
     * @return redirects to JSP  ;
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        return "account/login";
    }


    /**
     * Logout authenticated user;
     * @param request - HttpServletRequest;
     * @param response - HttpServletResponse;
     * @return - redirects to 'account/login' with request param 'logout';
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/account/login?logout";
    }


    /**
     * Method redirects to 'access denied' view, when user doesn't have privileges;
     * @param model ;
     * @return redirects to JSP page ;
     */
    @RequestMapping(value = "/access_denied",method = RequestMethod.GET)
    public String accessDeniedPage(Model model){
        return "account/access_denied";
    }



}
