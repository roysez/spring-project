package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.roysez.app.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String home(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "account/register";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveRegistration( User user,
                                   BindingResult result, ModelMap model) {

         userValidator.validate(user,result);

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "account/register";
        }

        if(user.getUserRole()!="USER")
            user.setUserRole("USER");

        userService.save(user);

        System.out.println("First Name : "+user.getFirstName());
        System.out.println("Last Name : "+user.getLastName());
        System.out.println("SSO ID : "+user.getSsoId());
        System.out.println("Password : "+user.getPassword());
        System.out.println("Email : "+user.getEmail());
        System.out.println("Checking UserRole...." + "\n" + "User Role: " + user.getUserRole());

        model.remove("user");
        model.addAttribute("successfulRegistration", "User " + user.getSsoId() + " has been registered successfully");
        return "redirect:/home";
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

        model.addAttribute("authenticatedUserName",getAuthenticatedUserName());
        return "account/access_denied";
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
