package com.springboot.vaadin.dao;

import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by maggouh on 13/02/17.
 */
@Repository
public class AccountDAO {

    @Autowired
    private static RoleDAO roleDAO;

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

    public void createAccount(String userName, String password, Set<Role> role) throws UsernameAlreadyUsedException {
        accounts.put(nextId++, new Account(userName, password, role));
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
