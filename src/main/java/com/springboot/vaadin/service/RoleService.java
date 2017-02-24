package com.springboot.vaadin.service;

import com.springboot.vaadin.dao.RoleDAO;
import com.springboot.vaadin.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maggouh on 13/02/17.
 */
@Service
public class RoleService {

    @Autowired
    RoleDAO roleDAO;


    public static Set<Role> getAllRole() {
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role(Role.ROLE_ADMIN));
        roles.add(new Role(Role.ROLE_TRAINEE));
        roles.add(new Role(Role.ROLE_USER));
        roles.add(new Role(Role.ROLE_VISITOR));

        return roles;
    }
}
