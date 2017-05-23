package org.roysez.app.dao;

import org.hibernate.Criteria;
import org.roysez.app.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link ArticleDao},
 * extends {@link AbstractDao};
 *
 * @author roysez
 */
@Repository("articleDao")
public class ArticleDaoImpl extends AbstractDao<Integer, Article> implements ArticleDao {

    public void save(Article article) {
        persist(article);
    }

    public Article findById(int id) {
        return getByKey(id);
    }

    public List<Article> findAll() {
        Criteria criteria = createEntityCriteria();
        List<Article> listOfAllArticles = criteria.list();
        return listOfAllArticles;
    }

    public void deleteArticle(Article article) {
        delete(article);
    }
}
