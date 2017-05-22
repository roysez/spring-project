package org.roysez.app.dao;

import org.roysez.app.model.User;

import java.util.List;

/**
 * DAO for the {@link User} objects.
 * Besides the basic CRUD methods it provides a method to load all Users.
 * Read methods: by {@code email,ssoId,Id} ;
 *
 *
 * Created by roysez on 28.04.2017.
 * 0:01
 * Package : org.roysez.app.dao
 */
public interface UserDao {

    /**
     * Save the persistent object.
     * @param user object to save
     */
    void save(User user);

    /**
     * Updates the persistent object.
     * @param user object to update
     */
    void updateUser(User user);

    /**
     * Get the object by id.
     * @param id ;
     * @return persistent object of type {@link User}
     */
    User findById(int id);


    /**
     * Get the object by field: 'ssoId'.
     * @param sso ;
     * @return persistent object of type {@link User}
     */
    User findBySSO(String sso);


    /**
     * Get the object by field: 'email'.
     * @param email ;
     * @return persistent object of type {@link User}
     */
    User findByEmail(String email);

    /**
     * Get the list of objects.
     * @return list of objects
     */
    List<User> findAll();

    /**
     * Delete the persistent object.
     * @param user object to delete
     */
    void deleteUser(User user);
}
