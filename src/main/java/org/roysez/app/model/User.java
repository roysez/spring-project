package org.roysez.app.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.roysez.app.enums.Role;
import org.roysez.app.enums.State;

import javax.persistence.*;

/**
 * Created by roysez on 02.05.2017.
 * 0:04
 * Package : org.roysez.app.model
 */
@Entity
@Table(name = "APP_USER")
/* Using lombok for getter,setter and etc. */
public @Data  class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "SSO_ID",unique = true,nullable = false)
    private String ssoId;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "EMAIL",nullable = false)
    private String email;

    @Column(name = "STATE",nullable = false)
    private String state = State.ACTIVE.getState();


    @NotEmpty
    @Column(name = "ROLE",nullable = false)
    private String UserRole = Role.USER.getRole();


}
