package org.roysez.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.roysez.app.enums.Role;
import org.roysez.app.enums.State;

import javax.persistence.*;
import java.util.Collection;

/**
 * Represents a User entity
 * Used { @Code Lombok }for getter, setter and etc.
 *
 * @author roysez
 */
@Entity
@Table(name = "APP_USER",
indexes = {
        @Index(columnList = "SSO_ID",unique = true,name = "SSO_ID_INDEX"),
        @Index(columnList = "EMAIL",unique = true,name = "EMAIL_INDEX")
})
@ToString(exclude = {"articles", "userProfilePhoto"})
public
@Data
class User {

    @OneToMany(targetEntity = Article.class, mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    @JsonIgnore
    protected Collection<Article> articles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "STATE", nullable = false)
    private String state = State.ACTIVE.getState();

    @NotEmpty
    @Column(name = "ROLE", nullable = false)
    private String UserRole = Role.USER.getRole();

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "PROFILE_PHOTO")
    @Lob
    private byte[] userProfilePhoto;


}
