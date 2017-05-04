package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by roysez on 04.05.2017.
 * 10:37
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{ssoId}",method = RequestMethod.GET)
    public String getUserProfile(Model model,
                                 @PathVariable("ssoId") String requestSsoId){
        User user = userService.findBySso(requestSsoId);
        if(user==null){
            model.addAttribute("errorUserNotFound","User with username:"+requestSsoId+" not found");
            System.out.println(model.containsAttribute("errorUserNotFound"));
            return "users/user_profile";
        }
        model.addAttribute("user",user);
        return "users/user_profile";
    }
}
