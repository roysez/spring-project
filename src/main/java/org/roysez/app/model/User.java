package org.roysez.app.model;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "SSO_ID",unique = true,nullable = false)
    private String ssoId;
    @NotEmpty
    @Column(name = "PASSWORD",nullable = false)
    private String password;
    @NotEmpty
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;
    @NotEmpty
    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;
    @NotEmpty
    @Column(name = "EMAIL",nullable = false)
    private String email;
    @NotEmpty
    @Column(name = "STATE",nullable = false)
    private String state = State.ACTIVE.getState();


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "APP_USER_USER_PROFILE",
//            joinColumns = { @JoinColumn(name = "USER_ID") },
//            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
//    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @NotEmpty
    @Column(name = "ROLE",nullable = false)
    private String UserRole = Role.USER.getRole();

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", state=" + state + "]";
    }
}
