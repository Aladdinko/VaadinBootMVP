package com.springboot.vaadin.ui;

import com.springboot.vaadin.security.AccessDeniedEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.mvp.MvpPresenterView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * Created by maggouh on 13/02/17.
 */
@UIScope
@Component
public class MainLayout extends VerticalLayout implements ViewDisplay, Button.ClickListener, ViewChangeListener {

    private Panel viewContainer;
    private HorizontalLayout navbar;

    private String key = UUID.randomUUID().toString();

    private Button buttonHome;
    private Button ButtonAdmin;
    private Button buttonLogin;
    private Button buttonLogout;

    @Autowired
    EventBus.UIEventBus eventBus;

    @PostConstruct
    public void postConstuct() {
        setSizeFull();
        eventBus.subscribe(this);

        navbar = new HorizontalLayout();
        navbar.setWidth("100%");
        navbar.setMargin(true);
        navbar.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        addComponent(navbar);

        final Label brand = new Label("Vaadin Spring Boot Example & MVP");
        brand.addStyleName(ValoTheme.LABEL_H2);
        brand.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        navbar.addComponent(brand);
        navbar.setComponentAlignment(brand, Alignment.MIDDLE_LEFT);
        navbar.setExpandRatio(brand, 1);

        buttonHome = new Button("Home", FontAwesome.HOME);
        buttonHome.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        buttonHome.setData(ViewToken.HOME);
        buttonHome.addClickListener(this);
        navbar.addComponent(buttonHome);

        ButtonAdmin = new Button("Admin home", FontAwesome.USER_MD);
        ButtonAdmin.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        ButtonAdmin.setData(ViewToken.ADMIN);
        ButtonAdmin.addClickListener(this);
        navbar.addComponent(ButtonAdmin);

        buttonLogin = new Button("Sign in", FontAwesome.SIGN_IN);
        buttonLogin.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        buttonLogin.setData(ViewToken.SIGNIN);
        buttonLogin.addClickListener(this);
        navbar.addComponent(buttonLogin);

        buttonLogout = new Button("Logout", FontAwesome.SIGN_OUT);
        buttonLogout.setData("-");
        buttonLogout.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        navbar.addComponent(buttonLogout);
        buttonLogout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                logout();
            }
        });

        viewContainer = new Panel();
        viewContainer.setSizeFull();
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
    }

    private void logout() {
        AnonymousAuthenticationToken authenticationToken = new AnonymousAuthenticationToken(key, "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
        UI.getCurrent().getNavigator().navigateTo(ViewToken.HOME);
        eventBus.publish(EventScope.UI, this, new UserSignedOutEvent());
    }

    @PreDestroy
    public void preDestroy() {
        eventBus.unsubscribe(this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        UI.getCurrent().getNavigator().navigateTo((String) event.getButton().getData());
    }

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
        for (int i = 0; i < navbar.getComponentCount(); i++) {

            if (navbar.getComponent(i) instanceof Button) {
                final Button btn = (Button) navbar.getComponent(i);
                btn.removeStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);

                String view = (String) btn.getData();

                if (event.getViewName().equals(view)) {
                    btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
                }
            }
        }
    }

    @Override
    public void showView(View view) {
//        if (vaadinSecurity.hasAuthority("ROLE_ADMIN")) {
//            displayAdminNavbar();
//        } else {
//            displayAnonymousNavbar();
//        }

        if (view instanceof MvpPresenterView) {
            viewContainer.setContent(((MvpPresenterView) view).getViewComponent());
        }
    }

    private void displayAnonymousNavbar() {

        buttonLogout.setVisible(false);
        buttonLogin.setVisible(true);
        buttonHome.setVisible(true);

    }

    private void displayAdminNavbar() {

        buttonLogout.setVisible(true);
        buttonLogin.setVisible(false);
        buttonHome.setVisible(true);

    }

    @EventBusListenerMethod
    public void onAccessDenied(org.vaadin.spring.events.Event<AccessDeniedEvent> event) {
        //TODO Redirect to login,

        if (event.getPayload().getCause() != null) {
            Notification.show("Access is denied.", "Service can be invoked only by Admin users.", Notification.Type.ERROR_MESSAGE);
        } else {
            Notification.show("Access is denied.", "You must sign in as Admin user before accessing Admin home.", Notification.Type.ERROR_MESSAGE);
        }
    }
}
