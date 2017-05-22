package org.roysez.app.controller;

import org.roysez.app.model.Article;
import org.roysez.app.model.User;
import org.roysez.app.service.ArticleService;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Controller used for handling requests such as:
 * 'GET' redirect the client to a logical view : '/articles', '/articles/{articleId}' ;
 * 'POST' for submitted form: '/articles' ;
 *  Invokes a business class to process business-related tasks ;
 *
 * Created by roysez on 13.05.2017.
 * 0:21
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/articles")
public class ArticleController {

    /**
     * Autowire by the implementation of {@link ArticleService},
     * defined in the Spring Container ;
     */
    @Autowired
    private ArticleService articleService;

    /**
     * Autowire by the implementation of {@link UserService},
     * defined in the Spring Container ;
     */
    @Autowired
    private UserService userService;


    /**
     * User to save given object of type {@link Article}
     * @param article - entity to post;
     * @param model ;
     * @return redirects to another url ;
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postArticle(Article article,Model model){

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.format(currentDate);

        article.setDate(currentDate);

        User user = userService.findBySso(HomeController.getAuthenticatedUserName());

        article.setUser(user);

        articleService.save(article);

        return "redirect:/articles/"+article.getId();
    }

    /**
     * Redirects to JSP, with given model;
     * Returns page with all articles;
     * @param model ;
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String getAllArticlesPage(Model model){
        List<Article> entityList = articleService.findAll();
        Collections.sort(entityList);
        model.addAttribute("articlesList",entityList);

        return "articles/articles";
    }

    /**
     * Redirects to JSP to form page with requested articles;
     * @param model ;
     * @param articleId - unique entity ID of {@link Article}
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public String getArticlePage(Model model, @PathVariable String articleId){

        if(!articleId.matches("[0-9]+"))
        {
            model.addAttribute("articleNotFound","Article with id: <i>"+articleId +"</i> not found");
        } else {
            Article article = articleService.findById(Integer.parseInt(articleId));

            if(article==null)
                model.addAttribute("articleNotFound","Article with id: <i>"+articleId +"</i> not found");
            else
                model.addAttribute("article",article);
        }

        return "articles/article";
    }



}
