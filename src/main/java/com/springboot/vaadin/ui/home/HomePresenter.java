package com.springboot.vaadin.ui.home;


import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.ui.MainUI;
import com.springboot.vaadin.ui.ViewToken;
import com.springboot.vaadin.ui.events.ActionEvent;
import com.springboot.vaadin.ui.events.ActionEventType;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;


/**
 * Created by maggouh on 13/02/17.
 */
@SpringView(name = ViewToken.HOME)
public class HomePresenter extends AbstractMvpPresenterView<HomeView> {

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    @Autowired
    HomeView view;

    EventBus.UIEventBus eventBus;

    public HomePresenter(HomeView view) {
        super(view);
        getView().setPresenter(this);
        eventBus = ((MainUI)MainUI.getCurrent()).getEventBus();
        eventBus.subscribe(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Authentication authentication = vaadinSharedSecurity.getAuthentication();
        getView().initView(authentication.getName());
    }

    @EventBusListenerMethod
    public void getHomeView(Event<ActionEvent> event) {
        if (event.getPayload().getActionEventType().equals(ActionEventType.HOME_AFTER_GENARATE_PDF)) {
            Page.getCurrent().setLocation(ViewToken.HOME);
        }
    }
}
