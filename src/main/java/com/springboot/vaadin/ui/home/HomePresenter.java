package com.springboot.vaadin.ui.home;


import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.service.AccesService;
import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;


/**
 * Created by maggouh on 13/02/17.
 */

@SpringView(name= ViewToken.HOME)
public class HomePresenter extends AbstractMvpPresenterView<HomeView> {

    @Autowired
    AccesService accesService;

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    @Autowired
    public HomePresenter(HomeView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenter(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Authentication authentication = vaadinSharedSecurity.getAuthentication();
        getView().initView(authentication.getName());
    }

}
