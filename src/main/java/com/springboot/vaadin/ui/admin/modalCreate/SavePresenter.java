package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Role;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.service.RoleService;
import com.springboot.vaadin.ui.events.AccountEvent;
import com.springboot.vaadin.ui.events.AccountEventType;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;

import java.util.Set;

/**
 * Created by maggouh on 21/02/17.
 */
@ViewScope
@Component
public class SavePresenter extends AbstractMvpPresenterView<ISaveView> implements SavePresenterHandlers {

    @Autowired
    RoleService roleService;

    @Autowired
    AccountService accountService;

    @Autowired
    public SavePresenter(SaveView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenterHandlers(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getView().initView();
    }

    @Override
    public Set<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @Override
    public void SaveAccount(String username, String password, Set<Role> roles) throws UsernameAlreadyUsedException {
        accountService.createAccount(username, password, roles);
        getEventBus().publish(EventScope.VIEW, this, new AccountEvent(AccountEventType.CREATE));
    }
}
