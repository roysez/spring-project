package org.roysez.app.controller;

import org.roysez.app.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by roysez on 13.05.2017.
 * 0:21
 * Package : org.roysez.app.controller
 */
@Controller
@RequestMapping(value = "/articles")
public class ArticleController {


    /*
     * Code Outlines
     * Doesn't work
     */

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postArticle(@ModelAttribute Article article,Model model){

        return "redirect:/articles/"+article.getId();
    }

    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public String getArticlePage(Model model){
        return "articles/article";
    }
}
