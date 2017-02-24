package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.springboot.vaadin.service.AccountService;
import com.springboot.vaadin.ui.ViewToken;
import com.springboot.vaadin.ui.admin.modalCreate.SavePresenter;
import com.springboot.vaadin.ui.events.AccountEvent;
import com.springboot.vaadin.ui.events.AccountEventType;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Window;
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
@Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
public class ListPresenter extends AbstractMvpPresenterView<IListView> implements ListPresenterHandlers {

    @Autowired
    SavePresenter savePresenter;

    @Autowired
    private AccountService accountService;

    @Autowired
    public ListPresenter(ListView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenterHandlers(this);
        eventBus.subscribe(this);
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
    public Collection<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Override
    public Window showView() {
        return savePresenter.getView().initView();
    }


    @EventBusListenerMethod
    public void onAccountEvent(Event<AccountEvent> event) {
        if (event.getPayload().getAccountEventType().equals(AccountEventType.CREATE)) {
            getView().refreshData();
        }
    }

}