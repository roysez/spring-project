package org.roysez.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by roysez on 28.04.2017.
 * 10:36
 * Package : org.roysez.app.util
 */
public class PasswordEncodingGenerator
{

    public static void main(String[] args) throws Exception{


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("qwerty"));
        System.out.println(passwordEncoder.matches("qwerty","$2a$10$GzJ7ucrISU88jgROb72Rv.x6rA30U/hYA69VQSm7xYxI.a6YT93dW"));


    }
}
