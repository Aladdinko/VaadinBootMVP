package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.mvp.MvpPresenterHandlers;
import com.springboot.vaadin.domain.Account;
import com.vaadin.ui.Window;

import java.util.Collection;

/**
 * Created by maggouh on 14/02/17.
 */
public interface ListPresenterHandlers extends MvpPresenterHandlers {

    public void editAccount(RTLTable table, boolean tag);

    public Collection<Account> getAllAccounts();

    public Window showView();
}
