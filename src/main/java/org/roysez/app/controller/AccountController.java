package org.roysez.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "account/access_denied";
    }
}
