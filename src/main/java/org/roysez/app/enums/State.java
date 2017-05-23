package org.roysez.app.enums;

/**
 * Enum describes conditions which may be UserProfile.
 *
 * @author roysez
 */
public enum State {


    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");

    private String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.state;
    }



}
