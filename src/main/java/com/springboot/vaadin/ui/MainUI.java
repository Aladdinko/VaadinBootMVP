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
import org.vaadin.spring.annotation.VaadinUI;
import org.vaadin.spring.events.EventBus;

import java.util.Locale;


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
    MainLayout mainLayout;

    @Override
    protected void init(VaadinRequest request) {
        setLocale(new Locale.Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build());
        getPage().setTitle("Vaadin Spring Security");

        SecuredNavigator securedNavigator = new SecuredNavigator(MainUI.this, mainLayout, springViewProvider, eventBus);
        securedNavigator.addProvider(springViewProvider);
        securedNavigator.addViewChangeListener(mainLayout);


        setContent(mainLayout);
        setErrorHandler(new SpringSecurityErrorHandler());
    }
}
