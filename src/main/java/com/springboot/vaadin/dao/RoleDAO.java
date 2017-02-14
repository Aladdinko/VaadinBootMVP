package com.springboot.vaadin.dao;

import com.springboot.vaadin.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
@Repository
public  class RoleDAO {

    public static List<Role> getRoleAdmin() {

        Role role = new Role();
        role.setName(Role.ROLE_ADMIN);
        Role role1 = new Role();
        role1.setName(Role.ROLE_USER);
        Role role2 = new Role();
        role2.setName(Role.ROLE_TRAINEE);
        Role role3 = new Role();
        role3.setName(Role.ROLE_VISITOR);
        List<Role> rolesAdmin = new ArrayList<Role>();

        rolesAdmin.add(role);
        rolesAdmin.add(role1);
        rolesAdmin.add(role2);
        rolesAdmin.add(role3);

        return rolesAdmin;
    }

    public static List<Role> getRoleUser() {

        Role role = new Role();
        role.setName(Role.ROLE_USER);
        Role role1 = new Role();
        role1.setName(Role.ROLE_TRAINEE);
        List<Role> rolesUser = new ArrayList<Role>();
        rolesUser.add(role);
        rolesUser.add(role1);

        return rolesUser;
    }

    public static List<Role> getRoleTrainee() {
        Role role = new Role();
        role.setName(Role.ROLE_TRAINEE);
        List<Role> roleTrainee = new ArrayList<Role>();
        roleTrainee.add(role);

        return roleTrainee;
    }

    public static List<Role> newRoles(String newRole) {

        Role role = new Role();
        role.setName(newRole);

        List<Role> newRoles = new ArrayList<Role>();
        newRoles.add(role);
        return newRoles;
    }

    public static List<Role> getRoleVisitor(){
        Role role = new Role();
        role.setName(Role.ROLE_VISITOR);
        List<Role> roleVisitor = new ArrayList<Role>();
        roleVisitor.add(role);

        return roleVisitor;
    }



}
