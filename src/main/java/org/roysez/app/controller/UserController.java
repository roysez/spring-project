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
import org.springframework.web.bind.annotation.RequestBody;
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

    /* Return page with list of all user */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllUsersPage(Model model){

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

    @RequestMapping(value = "/{ssoId}",method = RequestMethod.PUT)
    public ResponseEntity editUserProfile(Model model,@PathVariable("ssoId") String requestSsoId,
                                          @RequestBody User newUserInformation){
        if(!checkForAuthority(Role.ADMIN)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findBySso(requestSsoId);
        if (user == null) {
            System.out.println("Unable to edit. User with id " + requestSsoId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setSsoId(newUserInformation.getSsoId());
        user.setFirstName(newUserInformation.getFirstName());
        user.setLastName(newUserInformation.getLastName());
        user.setEmail(newUserInformation.getEmail());

        userService.update(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{ssoId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteUserProfile(@PathVariable String ssoId){


        if(!checkForAuthority(Role.ADMIN)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
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


    /*
    * Check authenticated user for having role which was requested
    */
    private Boolean checkForAuthority(Role roleToCheck){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth==null){
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();


        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_"+ roleToCheck)) {
                return true;
            }
        }
        return false;
    }

}
