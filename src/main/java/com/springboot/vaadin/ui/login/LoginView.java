package com.springboot.vaadin.ui.login;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;

/**
* Created by maggouh on 13/02/17.
*/
@Component
public class LoginView extends AbstractMvpView implements ILoginView, Button.ClickListener {

    private LoginPresenterHandlers loginPresenterHandlers;

    private VerticalLayout layout;
    private Label caption;
    private TextField username;
    private PasswordField password;
    private Button btnLogin;
    private VerticalLayout loginPanel;
    private Label errorMessage;

    @Override
    public void postConstruct() {
        super.postConstruct();
        setSizeFull();
        layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        setCompositionRoot(layout);


        loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.setMargin(true);
        layout.addComponent(loginPanel);
        layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        layout.setExpandRatio(loginPanel, 1);

        errorMessage = new Label();
        errorMessage.setWidth("300px");
        errorMessage.addStyleName(ValoTheme.LABEL_FAILURE);
        errorMessage.setVisible(false);
        loginPanel.addComponent(errorMessage);

        username = new TextField("Username");
        username.setImmediate(true);
        username.setWidth("300px");
        username.setNullRepresentation("");
        username.setInputPrompt("Enter your username");
        loginPanel.addComponent(username);

        password = new PasswordField("Password");
        password.setImmediate(true);
        password.setWidth("300px");
        password.setNullRepresentation("");
        loginPanel.addComponent(password);



        btnLogin = new Button("Signin", FontAwesome.UNLOCK);
        btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnLogin.addClickListener(this);
        btnLogin.setWidth("100%");
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginPanel.addComponent(btnLogin);

        final Label infoLabel = new Label(FontAwesome.INFO_CIRCLE.getHtml() + " You can sign in as: <br/>\"user\" with password \"user\" <br/>\"admin\" with password \"admin\".", ContentMode.HTML);
        infoLabel.setWidth("300px");
        loginPanel.addComponent(infoLabel);
    }


    @Override
    public void init() {
        errorMessage.setVisible(false);
        username.setValidationVisible(false);
        password.setValidationVisible(false);
        username.setValue(null);
        password.setValue(null);
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        if( username.getValue() != null && password.getValue() != null){
            if (event.getButton() == btnLogin) {
                loginPresenterHandlers.doSignIn(username.getValue(), password.getValue());
            }
        } else {
            Notification.show("Please enter username and password to logIn", Notification.Type.WARNING_MESSAGE);
        }

    }


    @Override
    public void setPresenterHandlers(
            LoginPresenterHandlers mvpPresenterHandlers) {
        this.loginPresenterHandlers = mvpPresenterHandlers;

    }


    @Override
    public void setErrorMessage(String error) {
        errorMessage.setValue(error);
        errorMessage.setVisible(true);

    }

}