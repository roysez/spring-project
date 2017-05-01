package org.roysez.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by roysez on 02.05.2017.
 * 0:26
 * Package : org.roysez.app.controller
 */
@Controller
public class HomeController {


    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String accessHomePage(Model model){
        return "home";
    }
}
