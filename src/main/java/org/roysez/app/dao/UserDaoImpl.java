package org.roysez.app.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.roysez.app.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link UserDao},
 * extends {@link AbstractDao};
 *
 * @author roysez
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    @CacheEvict(value = "users",key = "#user.ssoId")
    public void updateUser(User user) {
        update(user);
    }



    public User findById(int id) {
        return getByKey(id);
    }


    @Transactional(readOnly = true)
    public User findBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssoId", sso));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public List<User> findAll() {
        Criteria criteria = createEntityCriteria();
        List<User> listOfAllUsers = criteria.list();
        return listOfAllUsers;
    }

    @CacheEvict(value = "users",key = "#user.ssoId")
    public void deleteUser(User user) {
        delete(user);
    }

    public User findByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        User user = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
        return user;
    }
}
