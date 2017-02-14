package com.springboot.vaadin.ui.custom;

import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by maggouh on 13/02/17.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AccountService accountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().trim().toLowerCase();
        String password = authentication.getCredentials().toString().trim();

        Account user = (Account) accountService.loadUserByUsername(username);

        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {

            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}