package org.roysez.app.util;


import org.roysez.app.enums.Role;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorityUtil {

    @Autowired
    @Qualifier("userService")
    private  UserService userService;

    /**
     * Static method  for returning SsoId of authenticated user ;
     *
     * @return unique userName;
     */
    public static String getAuthenticatedUserName() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    /**
     * Check authenticated user for having role which was requested ;
     *
     * @param roleToCheck - requested role ;
     * @return Boolean value depending on the result ;
     */
    public  static Boolean checkForAuthority(Role roleToCheck) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_" + roleToCheck)) {
                return true;
            }
        }
        return false;
    }

    public   Boolean checkForOwnerOfProfile(String ssoId){

        return checkForAuthority(Role.ADMIN) ||
                ((UserDetails)SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()).getUsername()
                        .equals(userService.findBySso(ssoId).getSsoId());
    }
}
