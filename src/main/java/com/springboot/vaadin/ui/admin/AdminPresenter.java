package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.service.RoleService;
import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import java.util.Collection;
import java.util.List;

/**
 * Created by maggouh on 14/02/17.
 */

@SpringView(name = ViewToken.ADMIN)
//@Secured({"ROLE_ADMIN"})
public class AdminPresenter extends AbstractMvpPresenterView<IAdminView> implements AdminPresenterHandlers {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public AdminPresenter(AdminView view, EventBus eventBus) {
        super(view, eventBus);
        getView().setPresenterHandlers(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getView().initView();
    }

    @Override
    public void editAccount(RTLTable table, boolean tag) {
        accountService.setEditable(table, tag);
    }

    @Override
    public Collection<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @Override
    public List<String> getAllRole() {
        return roleService.getAllRole();
    }

    @Override
    public void createAccount(String username, String password, String roles) throws UsernameAlreadyUsedException {
        accountService.createAccount(username, password, roles);
    }
}