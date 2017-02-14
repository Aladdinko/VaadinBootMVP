package com.springboot.vaadin.service;

import com.springboot.vaadin.dao.RoleDAO;
import com.springboot.vaadin.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
@Service
public class RoleService {

    @Autowired
    RoleDAO roleDAO;


    public static List<String> getAllRole() {
        List<String> roles = new ArrayList<String>();
        roles.add(Role.ROLE_ADMIN);
        roles.add(Role.ROLE_TRAINEE);
        roles.add(Role.ROLE_USER);
        roles.add(Role.ROLE_VISITOR);

        return roles;
    }
}
