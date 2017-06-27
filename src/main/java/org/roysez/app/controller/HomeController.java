package org.roysez.app.controller;

import org.roysez.app.model.Article;
import org.roysez.app.service.UserService;
import org.roysez.app.util.AuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used for handling requests :
 * 'GET' : '/home' or '/' ;
 * Contains static method {@code getAuthenticatedUserName()} for returning SsoId of authenticated user ;
 *
 * @author roysez
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
     *
     * @param model ;
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String accessHomePage(Model model) {
        model.addAttribute("authenticatedUserName", AuthorityUtil.getAuthenticatedUserName());
        model.addAttribute("article", new Article());
        return "home";
    }
}
