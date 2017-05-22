package org.roysez.app.dao;

import org.roysez.app.model.Article;

import java.util.List;

/**
 * Created by roysez on 14.05.2017.
 * 0:23
 * Package : org.roysez.app.dao
 */
public interface ArticleDao {

    void save(Article article);
    Article findById(int id);
    List<Article> findAll();
    void deleteArticle(Article article);

}
