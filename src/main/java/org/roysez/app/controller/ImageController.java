package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller used for obtaining images from user profiles, which are stored in the database ;
 *
 * Created by roysez on 22.05.2017.
 * 2:05
 * Package : org.roysez.app.controller
 */
@Controller
public class ImageController {

    /**
     * Autowire by the implementation of {@link UserService},
     * defined in the Spring Container ;
     */
    @Autowired
    UserService userService;

    /**
     * Returns image in bytes array, which can be used at client side;
     * @param userId - unique user ID, which profile photo must be returned;
     * @return array of bytes, user profile photo;
     */
    @RequestMapping(value = "/image/{userId}")
    @ResponseBody
    public byte[] getUserProfilePhoto(@PathVariable int userId)  {
        User user = userService.findById(userId);
        return user.getUserProfilePhoto();
    }
}
