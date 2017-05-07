package org.roysez.app.controller;

import org.roysez.app.enums.Role;
import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.List;

/**
 * Created by roysez on 04.05.2017.
 * 10:37
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllUsers(Model model){
        List<User> listOfAllUsers = userService.findAll();
        model.addAttribute("listOfAllUsers",listOfAllUsers);
        return "users/users";
    }

    @RequestMapping(value = "/{ssoId}",method = RequestMethod.GET)
    public String getUserProfile(Model model,
                                 @PathVariable("ssoId") String requestSsoId){
        User user = userService.findBySso(requestSsoId);
        if(user==null){
            model.addAttribute("errorUserNotFound","User with username: <i>"+requestSsoId+"</i> not found");
            System.out.println(model.containsAttribute("errorUserNotFound"));
            return "users/user_profile";
        }
        model.addAttribute("user",user);
        return "users/user_profile";
    }

    //NOT WORKING
    @RequestMapping(value = "/{ssoId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteUserProfile(@PathVariable String ssoId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAllowed = false;

        if(auth==null){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_"+ Role.ADMIN)) {
                isAllowed = true;
                break;
            }
        }
        if (!isAllowed){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        User user = null;
        user = userService.findBySso(ssoId);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + ssoId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(user);

        return new ResponseEntity(HttpStatus.OK);
    }

}
