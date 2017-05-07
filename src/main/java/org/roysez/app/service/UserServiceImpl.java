package org.roysez.app.service;

import org.roysez.app.dao.UserDao;
import org.roysez.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by roysez on 28.04.2017.
 * 0:10
 * Package : org.roysez.app.service
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    public User findBySso(String sso) {
        return userDao.findBySSO(sso);
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
