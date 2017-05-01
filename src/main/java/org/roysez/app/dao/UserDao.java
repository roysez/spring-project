package org.roysez.app.dao;

import org.roysez.app.model.User;

/**
 * Created by roysez on 28.04.2017.
 * 0:01
 * Package : org.roysez.app.dao
 */
public interface UserDao {

    void save(User user);
    User findById(int id);
    User findBySSO(String sso);
}
