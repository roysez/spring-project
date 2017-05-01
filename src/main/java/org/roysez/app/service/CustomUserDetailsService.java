package org.roysez.app.service;

import org.roysez.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roysez on 02.05.2017.
 * 0:06
 * Package : org.roysez.app.service
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        User user = userService.findBySso(name);
        System.out.println(user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getSsoId(),user.getPassword(),
                user.getState().equals("Active"),
                true,true,true,getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add((new SimpleGrantedAuthority("ROLE_"+ user.getUserRole())));
        System.out.println("authorities:"+authorities);
        return authorities;
    }
}