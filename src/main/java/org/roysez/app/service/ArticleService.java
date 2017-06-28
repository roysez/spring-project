package org.roysez.app.service;

import org.roysez.app.model.Article;

import java.util.List;

/**
 * The {@link ArticleService} interface provides Business Layer methods for class {@link Article}
 *
 * @author roysez
 */
public interface ArticleService {

    /**
     * Save the persistent object.
     *
     * @param article object to save
     */
    void save(Article article);

    /**
     * Get the object by id.
     *
     * @param id ;
     * @return persistent object of type {@link Article}
     */
    Article findById(int id);

    /**
     * Get the list of objects.
     *
     * @return list of objects
     */
    List<Article> findAll();

    /**
     * Delete the persistent object.
     *
     * @param article object to delete
     */
    void deleteArticle(Article article);

    /**
     * Update the persistent object.
     *
     * @param article object to update
     */
    void updateArticle(Article article);
}
