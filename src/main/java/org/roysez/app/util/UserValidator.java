package org.roysez.app.util;


import org.apache.commons.validator.routines.EmailValidator;
import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validates the specified input and send the augmented validation result to the specified output.
 * Validates entity {@link User} ;
 *
 * Created by roysez on 03.05.2017.
 * 15:12
 * Package : org.roysez.app.util
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object object, Errors errors) {
        User userToValidate = (User) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ssoId","ssoId.empty","Username must not be empty.");
        if(userToValidate.getSsoId().length()>16){
            errors.rejectValue("ssoId", "ssoId.tooLong", "Username must not more than 16 characters.");
        }

        if(userService.findBySso(userToValidate.getSsoId())!=null){
            errors.rejectValue("ssoId", "ssoId.duplicate", "User with this username already exist, please pick another one");
        }
        if(userService.findByEmail(userToValidate.getEmail())!=null){
            errors.rejectValue("email", "email.duplicate", "This email is already in use ");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
        if(userToValidate.getPassword().length()<6 ){
            errors.rejectValue("password","password.incorrect","Password length must be more than 6 ");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "First name must not be empty.");
        if(userToValidate.getFirstName().length()>12){
            errors.rejectValue("firstName", "firstName.tooLong", "First name must not more than 12 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Last name must not be empty.");
        if(userToValidate.getLastName().length()>16){
            errors.rejectValue("lastName", "lastName.tooLong", "Last name must not more than 12 characters.");
        }


        if (!EmailValidator.getInstance().isValid(userToValidate.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }
    }
}
