package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import org.vaadin.spring.mvp.MvpPresenterHandlers;

import java.util.Collection;
import java.util.List;

/**
 * Created by maggouh on 14/02/17.
 */
public interface AdminPresenterHandlers extends MvpPresenterHandlers {

     void editAccount(RTLTable table, boolean tag);


     Collection<Account> getAllAccounts();


     List<String> getAllRole();


     void createAccount(String username, String password, String roles) throws UsernameAlreadyUsedException;

}
