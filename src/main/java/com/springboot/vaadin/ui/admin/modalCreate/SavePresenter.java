package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.presenter.MvpPresenter;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.service.RoleService;
import com.springboot.vaadin.ui.events.AccountEvent;
import com.springboot.vaadin.ui.events.AccountEventType;
import com.vaadin.data.util.BeanItem;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;

import java.util.List;

/**
 * Created by maggouh on 21/02/17.
 */
@ViewScope
@Component
public class SavePresenter implements MvpPresenter {

    @Autowired
    SaveView view;

    private final EventBus.ViewEventBus eventBus;

    private BeanItem<Account> accountBeanItem;
    @Autowired
    RoleService roleService;

    @Autowired
    AccountService accountService;

    @Autowired
    public SavePresenter(SaveView view, EventBus.ViewEventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;
        view.setPresenter(this);
    }

    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    public void saveAccount() throws UsernameAlreadyUsedException {
        accountService.createAccount(accountBeanItem);
        view.close();
        eventBus.publish(EventScope.VIEW, this, new AccountEvent(AccountEventType.CREATE));
    }

    public void setModelSave(BeanItem<Account> accountBeanItem) {
        view.setModel(accountBeanItem, getAllRole());
    }

    public void openWindowCreation() {
        this.accountBeanItem = new BeanItem<Account>(new Account());
        showWindow();
    }

    public void openWindowEdition(Account account) {
        this.accountBeanItem = new BeanItem<Account>(account);
        showWindow();
    }

    private void showWindow() {
        view.buildView();
        setModelSave(accountBeanItem);
        UI.getCurrent().addWindow(view);
    }

    @Override
    public com.vaadin.ui.Component getViewComponent() {
        return null;
    }
}
