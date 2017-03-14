package com.springboot.vaadin.components.window;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Created by maggouh on 27/02/17.
 */
public class RTLWindow extends Window {

    private VerticalLayout verticalLayout = new VerticalLayout();

    public RTLWindow() {
        setContent(verticalLayout);
        setMargin(true);

    }

    public void addComponent(Component c) {
        verticalLayout.addComponent(c);
    }

    public void removeAllComponents() {
        verticalLayout.removeAllComponents();
    }


    @Override
    public void addWindowModeChangeListener(WindowModeChangeListener listener) {
        super.addWindowModeChangeListener(listener);
    }

    public void setMargin(boolean enabled) {
        verticalLayout.setMargin(enabled);
    }
}
