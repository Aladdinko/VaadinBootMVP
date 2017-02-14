package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.service.RoleService;
import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenterView;

import java.util.Collection;
import java.util.List;

/**
 * Created by maggouh on 14/02/17.
 */
@UIScope
@SpringView(name= ViewToken.ADMIN)
@Secured({"ROLE_ADMIN"})
public class AdminPresenter extends AbstractMvpPresenterView<AdminPresenter.AdminView> implements AdminPresenterHandlers {

    public interface AdminView extends MvpView, MvpHasPresenterHandlers<AdminPresenterHandlers> {
        public void initView();

    }

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    public AdminPresenter(AdminView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

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