package org.roysez.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.roysez.app.exception.UserNotFoundException;
import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.roysez.app.util.AuthorityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller used for handling requests by the url '/users/*' ;
 * Invokes a business class to process business-related tasks,
 * redirects the client to a logical view ;
 *
 * @author roysez
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    /**
     * Autowire by the implementation of {@link UserService},
     * defined in the Spring Container ;
     */

    private UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Return page with list of all user
     *
     * @param model ;
     * @return jsp page;
     */
    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String getAllUsersPage(Model model,@RequestParam(required = false) String ssoId) {

        List<User> listOfAllUsers = userService.findAll();
        if(ssoId!=null){
            listOfAllUsers = listOfAllUsers.stream()
                    .parallel()
                    .filter(user -> StringUtils.containsIgnoreCase(user.getSsoId(),ssoId))
                    .collect(Collectors.toList());
        }
        model.addAttribute("listOfAllUsers", listOfAllUsers);
        return "users/users";
    }

    /**
     * Returning page with user profile, or give page with error-message if user was not found ;
     *
     * @param model        ;
     * @param requestSsoId - unique SsoID of user ;
     * @return page of user with given SsoID ;
     */
    @RequestMapping(value = "/{ssoId}", method = RequestMethod.GET)
    public String getUserProfile(Model model,
                                 @PathVariable("ssoId") String requestSsoId) {
        User user = userService.findBySso(requestSsoId);

        if (user == null) {
            throw new UserNotFoundException(requestSsoId);
        }

        model.addAttribute("user", user);
        return "users/user_profile";
    }

    /**
     * Handling request with JSON of new user data ;
     *
     * @param model              ;
     * @param requestSsoId       - unique SsoId of user ;
     * @param newUserInformation - new user data ;
     * @return {@code ResponseEntity} with some HTTP Status Code ;
     */
    @RequestMapping(value = "/{ssoId}", method = RequestMethod.PUT)
    public ResponseEntity editUserProfile(Model model, @PathVariable("ssoId") String requestSsoId,
                                          @RequestBody User newUserInformation) {

        if (!AuthorityUtil.checkForOwnerOfProfile(requestSsoId)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findBySso(requestSsoId);
        if (user == null) {
            logger.warn("Unable to edit. User with id " + requestSsoId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setSsoId(newUserInformation.getSsoId());
        user.setFirstName(newUserInformation.getFirstName());
        user.setLastName(newUserInformation.getLastName());
        user.setEmail(newUserInformation.getEmail());
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Method which handling request for deleting user entity ;
     *
     * @param ssoId - unique SsoId of user ;
     * @return {@link ResponseEntity} with some HTTP Status Code ;
     */
    @RequestMapping(value = "/{ssoId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserProfile(@PathVariable String ssoId) {

        if (!AuthorityUtil.checkForOwnerOfProfile(ssoId)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        User user = null;
        user = userService.findBySso(ssoId);
        if (user == null) {
            logger.warn("Unable to delete. User with id " + ssoId + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * Method for handling request to set user profile photo;
     *
     * @param fileImage - file from client side;
     * @param userId    - unique userId;
     * @param model     ;
     * @return page of user profile, with given id {@param userId};
     */
    @RequestMapping(value = "/{userId}/photo", method = RequestMethod.POST)
    public String setUserProfilePhoto(@RequestParam("file") MultipartFile fileImage,
                                      @PathVariable Integer userId, Model model) {

        if (fileImage.isEmpty()) {
            model.addAttribute("error", "Choose any file");
            return "users/user_profile";
        }
        User user = userService.findById(userId);

        if(!AuthorityUtil.checkForOwnerOfProfile(user.getSsoId())) return "account/access_denied";

        try {
            byte[] bytes = fileImage.getBytes();
            user.setUserProfilePhoto(bytes);
            userService.update(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/users/" + user.getSsoId();
    }


}
