package com.springboot.vaadin.ui.login;

import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.ui.ViewToken;
import com.springboot.vaadin.ui.custom.CustomAuthenticationProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import java.util.Collection;


/**
 * Created by maggouh on 13/02/17.
 */
@SpringView(name = ViewToken.SIGNIN)
public class LoginPresenter extends AbstractMvpPresenterView<LoginView> {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    VaadinSession vaadinSession;

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;


    @Autowired
    public LoginPresenter(LoginView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenter(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getView().init();
    }

    public void doSignIn(String username, String password) {
        try {

            final SecurityContext securityContext = SecurityContextHolder.getContext();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            final Authentication authentication = customAuthenticationProvider.authenticate(token);
            securityContext.setAuthentication(authentication);
            UI.getCurrent().getNavigator().navigateTo(ViewToken.LIST);

        } catch (AuthenticationException e) {
            getView().setErrorMessage(e.getMessage());
        }

    }


    public boolean userHasAuthority(String username, String password, String authority) {

        Authentication authentication = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }


}