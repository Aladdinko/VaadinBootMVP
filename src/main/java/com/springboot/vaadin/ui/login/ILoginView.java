package com.springboot.vaadin.ui.login;

import com.springboot.vaadin.components.mvp.MvpHasPresenterHandlers;
import com.springboot.vaadin.components.mvp.MvpView;

/**
 * Created by maggouh on 17/02/17.
 */
public interface ILoginView extends MvpView, MvpHasPresenterHandlers<LoginPresenterHandlers> {
    void init();
    void setErrorMessage(String errorMessage);
}