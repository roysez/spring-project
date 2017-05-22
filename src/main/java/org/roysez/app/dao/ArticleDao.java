package org.roysez.app.dao;

import org.roysez.app.model.Article;

import java.util.List;

/**
 * DAO for the {@link Article} objects.
 * Besides the basic CRUD methods it provides a method to load all Articles.
 *
 * Created by roysez on 14.05.2017.
 * 0:23
 * Package : org.roysez.app.dao
 */
public interface ArticleDao {

    /**
     * Save the persistent object.
     * @param article object to save
     */
    void save(Article article);


    /**
     * Get the object by id.
     * @param id ;
     * @return persistent object of type {@link Article}
     */
    Article findById(int id);

    /**
     * Get the list of objects.
     * @return list of objects
     */
    List<Article> findAll();

    /**
     * Delete the persistent object.
     * @param article object to delete
     */
    void deleteArticle(Article article);

}
