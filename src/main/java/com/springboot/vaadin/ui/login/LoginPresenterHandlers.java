package com.springboot.vaadin.ui.login;

import org.vaadin.spring.mvp.MvpPresenterHandlers;

/**
* Created by maggouh on 13/02/17.
*/
public interface LoginPresenterHandlers extends MvpPresenterHandlers {

    void doSignIn(String username, String password);

}