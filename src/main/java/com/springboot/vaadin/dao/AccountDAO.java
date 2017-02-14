package com.springboot.vaadin.dao;

import com.google.inject.Inject;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maggouh on 13/02/17.
 */
@Repository
public class AccountDAO {

    @Inject
    static RoleDAO roleDAO;

    private static Map<Integer, Account> accounts;

    private static Account admin = new Account("admin", "admin", roleDAO.getRoleAdmin());
    private static Account user = new Account("user", "user", roleDAO.getRoleUser());
    private static Account trainee = new Account("trainee", "trainee", roleDAO.getRoleTrainee());
    private static Account visitor = new Account("visitor", "visitor", roleDAO.getRoleVisitor());

    private static int nextId = 1;

    static {
        accounts = new HashMap<Integer, Account>() {
            @Override
            public Account put(Integer key, Account value) {

                return super.put(key, value);
            }
        };
        accounts.put(nextId++, admin);
        accounts.put(nextId++, user);
        accounts.put(nextId++, trainee);
        accounts.put(nextId++, visitor);
    }

    public void createAccount(String userName, String password, String role) throws UsernameAlreadyUsedException {
        accounts.put(nextId++, new Account(userName, password, roleDAO.newRoles(role)));
    }

    public Account findAccountByUsername(String username) {

        if (username.equals(admin.getUsername())) {
            return admin;
        }

        if (username.equals(user.getUsername())) {
            return user;
        }

        if(username.equals(trainee.getUsername())) {
            return trainee;
        }

        if(username.equals(visitor.getUsername())) {
            return visitor;
        }
        return null;
    }

    public Collection<Account> getAllAccounts() {
        return this.accounts.values();
    }
}