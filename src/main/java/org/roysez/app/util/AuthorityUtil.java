package org.roysez.app.util;


import org.roysez.app.enums.Role;
import org.roysez.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorityUtil {

    @Autowired
    UserService userService;

    /**
     * Check authenticated user for having role which was requested ;
     *
     * @param roleToCheck - requested role ;
     * @return Boolean value depending on the result ;
     */
    public  Boolean checkForAuthority(Role roleToCheck) {
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

    public  Boolean checkForOwnerOfProfile(String ssoId){

        return checkForAuthority(Role.ADMIN) ||
                ((UserDetails)SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()).getUsername()
                        .equals(userService.findBySso(ssoId).getSsoId());
    }
}
