package org.roysez.app.enums;

/**
 * Enum describes conditions which may be UserProfile.
 *
 * Created by roysez on 02.05.2017.
 * 0:06
 * Package : org.roysez.app.enums
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

    public String getName(){
        return this.getName();
    }

}
