package com.springboot.vaadin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

/**
 * Created by maggouh on 13/03/17.
 */
@Service
public class AccesService {

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminShowEcho(String s) {
            return "I am admin:" + s;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String authenticatShowEcho(String s) {
                return "I am authenticated user: " + s;
    }
}
