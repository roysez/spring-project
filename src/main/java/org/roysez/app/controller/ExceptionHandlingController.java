package org.roysez.app.controller;

import org.roysez.app.exception.ArticleNotFoundException;
import org.roysez.app.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFoundHandler(Exception ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("users/user_profile");
        modelAndView.addObject("error","User with username: <i>" + ex.getMessage() + "</i> not found.");
        return modelAndView;
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ModelAndView articleNotFoundHandler(Exception ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("articles/article");
        modelAndView.addObject("articleNotFound", "Article with id: <i>" + ex.getMessage() + "</i> not found");
        return modelAndView;
    }
}
