package org.roysez.app.service;

import org.roysez.app.dao.ArticleDao;
import org.roysez.app.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link ArticleService}
 *
 * @author roysez
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    public void save(Article article) {
        articleDao.save(article);
    }

    public Article findById(int id) {
        return articleDao.findById(id);
    }

    public List<Article> findAll() {
        return articleDao.findAll();
    }

    public void deleteArticle(Article article) {
        articleDao.deleteArticle(article);
    }


    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }
}
