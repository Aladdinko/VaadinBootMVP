package com.springboot.vaadin.service;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.dao.AccountDAO;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by maggouh on 13/02/17.
 */
@Service
public class AccountService implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountDAO.findAccountByUsername(username);
            return account;
        } catch (UsernameNotFoundException e) {
            LOG.debug("Account not found", e);
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
//    @PreAuthorize("hasRole('"+ Role.ROLE_ADMIN + "')")
    public void createAccount(String username, String password, String role) throws UsernameAlreadyUsedException {
        accountDAO.createAccount(username, password, role);
    }


//    @PreAuthorize("hasRole('"+ Role.ROLE_ADMIN + "')")
    public void setEditable(RTLTable table ,boolean tag) {
        table.setEditable(tag);
    }
}
