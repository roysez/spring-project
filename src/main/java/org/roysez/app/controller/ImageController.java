package org.roysez.app.controller;

import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by roysez on 22.05.2017.
 * 2:05
 * Package : org.roysez.app.controller
 */
@Controller
public class ImageController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/image/{userId}")
    @ResponseBody
    public byte[] getUserProfilePhoto(@PathVariable int userId)  {
        User user = userService.findById(userId);
        return user.getUserProfilePhoto();
    }
}
