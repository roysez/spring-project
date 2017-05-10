package org.roysez.app.service;

import org.roysez.app.model.User;

import java.util.List;

/**
 * Created by roysez on 27.04.2017.
 * 23:14
 * Package : org.roysez.app.service
 */
public interface UserService {

    User findBySso(String sso);
    User findById(int id);
    User findByEmail(String email);
    List<User> findAll();
    void save(User user);
    void deleteUser(User user);


}
