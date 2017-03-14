package com.springboot.vaadin.service;

import com.springboot.vaadin.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
@Service
public class RoleService {

    public static List<Role> getAllRole() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role(Role.ROLE_ADMIN));
        roles.add(new Role(Role.ROLE_USER));
        roles.add(new Role(Role.ROLE_TRAINEE));
        roles.add(new Role(Role.ROLE_VISITOR));

        return roles;
    }


}
