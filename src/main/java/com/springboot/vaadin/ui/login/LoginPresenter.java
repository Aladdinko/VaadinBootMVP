package com.springboot.vaadin.ui.login;

import com.springboot.vaadin.ui.ViewToken;
import com.springboot.vaadin.ui.custom.CustomAuthenticationProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenterView;

import java.util.Collection;


/**
* Created by maggouh on 13/02/17.
*/
@UIScope
@SpringView(name= ViewToken.SIGNIN)
public class LoginPresenter extends AbstractMvpPresenterView<LoginPresenter.LoginView> implements LoginPresenterHandlers {

    public interface LoginView extends MvpView, MvpHasPresenterHandlers<LoginPresenterHandlers> {
        void init();
        void setErrorMessage(String errorMessage);
    }

    @Autowired
    public LoginPresenter(LoginView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenterHandlers(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getView().init();
    }

    @Override
    public void doSignIn(String username, String password) {

        try {
			/*
			 * security.login(username, password);
			 *
			 */
            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
            AuthenticationProvider authenticationProvider = (AuthenticationProvider) ctx.getBean("customAuthenticationProvider");
            Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            //Redirect to UserHome or Admin Home
            if (userHasAuthority("ROLE_ADMIN", authentication)) {
                UI.getCurrent().getNavigator().navigateTo(ViewToken.ADMIN);
            }

        } catch (AuthenticationException e) {
            getView().setErrorMessage(e.getMessage());
        }

    }
    public boolean userHasAuthority(String authority, Authentication authentication) {

        String username = authentication.getName().trim().toLowerCase();
        String password = authentication.getCredentials().toString().trim();
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        AuthenticationProvider authenticationProvider = ctx.getBean(CustomAuthenticationProvider.class);
        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }


}