package com.springboot.vaadin.security;

import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Page;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;


/**
 * Created by maggouh on 13/02/17.
 */
public class SecuredNavigator extends Navigator {


    final SpringViewProvider viewProvider;
    final EventBus.UIEventBus eventBus;

    public SecuredNavigator(UI ui, ViewDisplay display, SpringViewProvider viewProvider, EventBus.UIEventBus eventBus) {
        super(ui, display);
        this.viewProvider = viewProvider;
        this.eventBus = eventBus;
        addProvider(this.viewProvider);
    }

    /*
     * (non-Javadoc)
     * @see com.vaadin.navigator.Navigator#navigateTo(java.lang.String)
     */
    @Override
    public void navigateTo(String navigationState) {

        if (ViewToken.VALID_TOKENS.contains(navigationState)) {

			/* This method in class should be public
			 * private boolean isViewBeanNameValidForCurrentUI(String beanName)
			 *
			 *  workaround
			 */
            if ( viewProvider.getView(navigationState) == null ) {

				/*
				 * Handle entering UI from bookmark
				 */
                String uriFragment = extractViewToken();
                if (uriFragment.equals(navigationState)) {

                    super.navigateTo(ViewToken.HOME);

                } else {
					/*
	            	 * View is probably @Secured
	            	 */
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