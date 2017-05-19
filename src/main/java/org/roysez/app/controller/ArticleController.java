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
import java.util.Date;

/**
 * Created by roysez on 13.05.2017.
 * 0:21
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;


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
