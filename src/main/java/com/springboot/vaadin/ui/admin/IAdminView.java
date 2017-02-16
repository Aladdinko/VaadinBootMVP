package com.springboot.vaadin.ui.admin;


import com.springboot.vaadin.components.mvp.MvpHasPresenterHandlers;
import com.springboot.vaadin.components.mvp.MvpView;

/**
 * Created by maggouh on 15/02/17.
 */
public interface IAdminView extends MvpView, MvpHasPresenterHandlers<AdminPresenterHandlers> {
    void initView();
}