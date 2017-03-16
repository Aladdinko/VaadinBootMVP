package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.ui.MainUI;
import com.springboot.vaadin.ui.ViewToken;
import com.springboot.vaadin.ui.admin.modalCreate.SavePresenter;
import com.springboot.vaadin.ui.events.AccountEvent;
import com.springboot.vaadin.ui.events.AccountEventType;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.util.Collection;

/**
 * Created by maggouh on 14/02/17.
 */

@SpringView(name = ViewToken.LIST)
@Secured({Role.ROLE_ADMIN, Role.ROLE_USER, Role.ROLE_TRAINEE})
public class ListPresenter extends AbstractMvpPresenterView<ListView> {

    @Autowired
    SavePresenter savePresenter;

    @Autowired
    private AccountService accountService;

    EventBus.UIEventBus eventBus;

    public ListPresenter(ListView view) {
        super(view);
        getView().setPresenter(this);
        eventBus = ((MainUI)MainUI.getCurrent()).getEventBus();
        eventBus.subscribe(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getView().buildView();
    }

    public Collection<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public void openWindowCreation() {
        savePresenter.openWindowCreation();
    }

    public void openWindowEdition(Account accountSelected) {
        savePresenter.openWindowEdition(accountSelected);
    }

    public void setModelPresenter(Collection<Account> accounts) {
        getView().setModel(accounts);
    }

    @EventBusListenerMethod
    public void onAccountEvent(Event<AccountEvent> event) {
        if (event.getPayload().getAccountEventType().equals(AccountEventType.CREATE)) {
            getView().refreshData();
        }
    }
}