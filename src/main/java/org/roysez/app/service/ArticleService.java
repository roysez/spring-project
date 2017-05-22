package org.roysez.app.service;

import org.roysez.app.model.Article;

import java.util.List;

/**
 * Created by roysez on 18.05.2017.
 * 12:49
 * Package : org.roysez.app.service
 */
public interface ArticleService {

    void save(Article article);
    Article findById(int id);
    List<Article> findAll();
    void deleteArticle(Article article);

}
