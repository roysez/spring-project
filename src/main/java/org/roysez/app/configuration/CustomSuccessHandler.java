package org.roysez.app.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by roysez on 28.04.2017.
 * 2:12
 * Package : org.roysez.app.configuration
 */


public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url="";
        String userName = ((UserDetails)authentication.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles =  new ArrayList<String>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        if (isAdmin(roles)) {
            url = "/home";
        } else if (isUser(roles)) {
            url = "/users/"+userName;
        } else {
            url="/account/access_denied";
        }

        return url;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isUser(List<String> roles) {
        return roles.contains("ROLE_USER");

    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }


}
