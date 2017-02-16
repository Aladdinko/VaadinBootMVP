package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by maggouh on 21/02/17.
 */
@UIScope
@Component
public class SaveView extends AbstractMvpView implements ISaveView {

    private Window window;

    private SavePresenterHandlers savePresenterHandlers;

    @Override
    public void setPresenterHandlers(SavePresenterHandlers savePresenterHandlers) {
        this.savePresenterHandlers = savePresenterHandlers;
    }

    public Window getWindow() {

        final VerticalLayout popupContent = new VerticalLayout();
        final Window window = new Window("Create Accout View", popupContent);

        final TextField username = new TextField("Username : ");
        final TextField password = new TextField("Password : ");
        final TwinColSelect roles = new TwinColSelect("Roles :");

        roles.setLeftColumnCaption("Select role(s) :");
        roles.setRightColumnCaption("role(s) selected :");
        roles.setMultiSelect(true);

        List<String> authorities = savePresenterHandlers.getAllRole();
        BeanItemContainer<String> container = new BeanItemContainer<String>(String.class, authorities);

        roles.setContainerDataSource(container);

        popupContent.addComponent(username);
        popupContent.addComponent(password);
        popupContent.addComponent(roles);

        Button button = new Button("Save Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    savePresenterHandlers.SaveAccount(username.getValue(), password.getValue(), roles.getValue().toString());
                    window.close();
                    Page.getCurrent().reload();
                } catch (UsernameAlreadyUsedException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setStyleName(ValoTheme.BUTTON_PRIMARY);
        button.setIcon(FontAwesome.PLUS);
        popupContent.addComponent(button);
        popupContent.setMargin(true);

        return window;
    }

    @Override
    public Window initView() {
        setSizeFull();
        window = getWindow();
        window.center();
        return window;
    }
}
