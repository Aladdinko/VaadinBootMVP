package com.springboot.vaadin.dao;

import com.springboot.vaadin.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maggouh on 13/02/17.
 */
@Repository
public  class RoleDAO {

    public static Set<Role> getRoleAdmin() {

        Role role = new Role();
        role.setName(Role.ROLE_ADMIN);
        Role role1 = new Role();
        role1.setName(Role.ROLE_USER);
        Role role2 = new Role();
        role2.setName(Role.ROLE_TRAINEE);
        Role role3 = new Role();
        role3.setName(Role.ROLE_VISITOR);
        Set<Role> rolesAdmin = new HashSet<Role>();

        rolesAdmin.add(role);
        rolesAdmin.add(role1);
        rolesAdmin.add(role2);
        rolesAdmin.add(role3);

        return rolesAdmin;
    }

    public static Set<Role> getRoleUser() {

        Role role = new Role();
        role.setName(Role.ROLE_USER);
        Role role1 = new Role();
        role1.setName(Role.ROLE_TRAINEE);
        Set<Role> rolesUser = new HashSet<Role>();
        rolesUser.add(role);
        rolesUser.add(role1);

        return rolesUser;
    }

    public static Set<Role> getRoleTrainee() {
        Role role = new Role();
        role.setName(Role.ROLE_TRAINEE);
        Set<Role> roleTrainee =  new HashSet<Role>();
        roleTrainee.add(role);

        return roleTrainee;
    }

    public static Set<Role> getRoleVisitor(){
        Role role = new Role();
        role.setName(Role.ROLE_VISITOR);
        Set<Role> roleVisitor = new HashSet<Role>();
        roleVisitor.add(role);

        return roleVisitor;
    }



}
