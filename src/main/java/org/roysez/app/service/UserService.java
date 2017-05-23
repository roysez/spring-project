package org.roysez.app.service;

import org.roysez.app.model.User;

import java.util.List;

/**
 * The {@code UserService} interface provides Business Layer methods for class {@code User}
 *
 * @author roysez
 */
public interface UserService {

    User findBySso(String sso);

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void deleteUser(User user);


}
