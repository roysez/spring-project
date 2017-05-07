package org.roysez.app.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.roysez.app.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roysez on 28.04.2017.
 * 0:02
 * Package : org.roysez.app.dao
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer,User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    public User findById(int id) {
        return getByKey(id);
    }

    public User findBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssoId",sso));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public List<User> findAll() {
        Criteria criteria = createEntityCriteria();
        List<User> listOfAllUsers =  new ArrayList<User>();
        listOfAllUsers = criteria.list();
        return  listOfAllUsers;
    }

    public void deleteUser(User user) {
        delete(user);
    }
}
