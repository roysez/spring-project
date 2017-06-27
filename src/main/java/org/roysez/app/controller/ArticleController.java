package org.roysez.app.controller;

import org.roysez.app.exception.ArticleNotFoundException;
import org.roysez.app.model.Article;
import org.roysez.app.model.User;
import org.roysez.app.service.ArticleService;
import org.roysez.app.service.UserService;
import org.roysez.app.util.AuthorityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
 * Invokes a business class to process business-related tasks ;
 *
 * @author roysez
 */
@Controller
@RequestMapping(value = "/articles")
public class ArticleController {

    @Autowired
    private AuthorityUtil authorityUtil;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
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
     *
     * @param article - entity to post;
     * @param model   ;
     * @return redirects to another url ;
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postArticle(Article article, Model model) {

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.format(currentDate);
        article.setDate(currentDate);

        User user = userService.findBySso(HomeController.getAuthenticatedUserName());
        article.setUser(user);
        articleService.save(article);
        return "redirect:/articles/" + article.getId();
    }

    /**
     * Redirects to JSP, with given model;
     * Returns page with all articles;
     *
     * @param model ;
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getAllArticlesPage(Model model) {
        List<Article> entityList = articleService.findAll();
        Collections.sort(entityList);
        model.addAttribute("articlesList", entityList);
        return "articles/articles";
    }

    /**
     * Redirects to JSP to form page with requested articles;
     *
     * @param model     ;
     * @param articleId - unique entity ID of {@link Article}
     * @return name of JSP to redirect ;
     */
    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String getArticlePage(Model model, @PathVariable String articleId)
            throws ArticleNotFoundException {

        if (!articleId.matches("[0-9]+")) {
            throw new ArticleNotFoundException(articleId);
        } else {
            Article article = articleService.findById(Integer.parseInt(articleId));
            if (article == null)
                throw new ArticleNotFoundException(articleId);
            else
                model.addAttribute("article", article);
        }
        return "articles/article";
    }


    /**
     * Handling request with JSON of new article data ;
     *
     * @param model ;
     * @param requestId  - unique Id of article ;
     * @param newArticle - new article data ;
     * @return {@link ResponseEntity} with some HTTP Status Code ;
     */
    @RequestMapping(value = "/{ssoId}", method = RequestMethod.PUT)
    public ResponseEntity editArticle(Model model, @PathVariable("id") String requestId,
                                          @RequestBody Article newArticle) {


        if (!requestId.matches("[0-9]+")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Article article = articleService.findById(Integer.getInteger(requestId));
        if (article == null) {
            logger.warn("Unable to edit. Article with id " + requestId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!authorityUtil.checkForOwnerOfProfile(article.getUser().getSsoId())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        newArticle.setId(article.getId());
        articleService.updateArticle(newArticle);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Method which handling request for deleting article entity ;
     *
     * @param articleId - unique Id of article ;
     * @return {@link ResponseEntity} with some HTTP Status Code ;
     */
    @RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteArticle(@PathVariable String articleId) {


        if (!articleId.matches("[0-9]+")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Article article = articleService.findById(Integer.getInteger(articleId));
        if (article == null) {
            logger.warn("Unable to delete. Article with id " + articleId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (!authorityUtil.checkForOwnerOfProfile(article.getUser().getSsoId())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        articleService.deleteArticle(article);
        return new ResponseEntity(HttpStatus.OK);
    }

}
