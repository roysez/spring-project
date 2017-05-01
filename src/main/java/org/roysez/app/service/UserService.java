package org.roysez.app.service;

import org.roysez.app.model.User;

/**
 * Created by roysez on 27.04.2017.
 * 23:14
 * Package : org.roysez.app.service
 */
public interface UserService {

    User findBySso(String sso);
    User findById(int id);
    void save(User user);

}
