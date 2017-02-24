package com.springboot.vaadin.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by maggouh on 13/02/17.
 */
public class Role implements GrantedAuthority {

    private String name;

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_TRAINEE = "ROLE_TRAINEE";
    public static final String ROLE_VISITOR = "ROLE_VISITOR";

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
