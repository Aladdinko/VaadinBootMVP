package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.mvp.MvpPresenterHandlers;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;

import java.util.Collection;
import java.util.List;

/**
 * Created by maggouh on 14/02/17.
 */
public interface AdminPresenterHandlers extends MvpPresenterHandlers {

    public void editAccount(RTLTable table, boolean tag);

    public Collection<Account> getAllAccounts();

    public List<String> getAllRole();

    public void createAccount(String username, String password, String roles) throws UsernameAlreadyUsedException;

}
