package com.springboot.vaadin.security;

import com.springboot.vaadin.ui.MainUI;
import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Page;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;


/**
 * Created by maggouh on 13/02/17.
 */
public class SecuredNavigator extends Navigator {

    final VaadinSharedSecurity vaadinSharedSecurity;
    final SpringViewProvider viewProvider;

    private EventBus.UIEventBus eventBus;

    public SecuredNavigator(UI ui, ViewDisplay display,VaadinSharedSecurity vaadinSharedSecurity, SpringViewProvider viewProvider) {
        super(ui, display);
        this.viewProvider = viewProvider;
        this.vaadinSharedSecurity = vaadinSharedSecurity;
        eventBus = ((MainUI)MainUI.getCurrent()).getEventBus();
        addProvider(this.viewProvider);
    }

    @Override
    public void navigateTo(String navigationState) {

        if (ViewToken.VALID_TOKENS.contains(navigationState)) {

            if ( viewProvider.getView(navigationState) == null ) {

                String uriFragment = extractViewToken();
                if (uriFragment.equals(navigationState)) {

                    super.navigateTo(ViewToken.HOME);

                } else {

                    eventBus.publish(EventScope.UI, UI.getCurrent(), new AccessDeniedEvent(navigationState));
                }

            } else {
                super.navigateTo(navigationState);
            }

        } else {
            //invalid ViewToken
            super.navigateTo(ViewToken.HOME);
        }

    }


    private String extractViewToken() {
        String uriFragment = Page.getCurrent().getUriFragment();
        if (uriFragment != null && !uriFragment.isEmpty() && uriFragment.length() > 1) {
            return uriFragment.substring(1);
        } else {
            return "";
        }

    }


}