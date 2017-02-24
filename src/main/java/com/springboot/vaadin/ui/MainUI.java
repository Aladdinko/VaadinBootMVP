package com.springboot.vaadin.ui;

import com.springboot.vaadin.security.SecuredNavigator;
import com.springboot.vaadin.security.SpringSecurityErrorHandler;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

//import org.vaadin.spring.security.util.SecurityExceptionUtils;


/**
 * Created by maggouh on 13/02/17.
 */
@SpringUI(path = "/")
@Title("Vaadin4Spring Security Demo")
@Theme("valo")
public class MainUI extends UI {

    @Autowired
    SpringViewProvider springViewProvider;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    @Autowired
    MainLayout mainLayout;

    @Override
    protected void init(VaadinRequest request) {

        getPage().setTitle("Vaadin Spring Boot Security");

        SecuredNavigator securedNavigator = new SecuredNavigator(MainUI.this, mainLayout, vaadinSharedSecurity, springViewProvider, eventBus);
        securedNavigator.addProvider(springViewProvider);
        securedNavigator.addViewChangeListener(mainLayout);
        setContent(mainLayout);
        setErrorHandler(new SpringSecurityErrorHandler());

    }

}
