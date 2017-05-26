package org.roysez.app.enums;

/**
 * Enum gives types of User Roles;
 *
 * @author roysez
 */
public enum Role {

    /**
     * role: ADMIN;
     */
    ADMIN("ADMIN"),
    /**
     * role: USER;
     * less privileged than {@code Role.ADMIN}
     */
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.role;
    }

}
