package com.springboot.vaadin.dao.exception;

/**
 * Created by maggouh on 13/02/17.
 */
public class UsernameAlreadyUsedException extends Exception {
    public UsernameAlreadyUsedException(String username) {
        super("The username '" + username + "' is already in use.");
    }
}
