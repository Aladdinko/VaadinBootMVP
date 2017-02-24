package com.springboot.vaadin.ui.admin;


import com.springboot.vaadin.components.mvp.MvpHasPresenterHandlers;
import com.springboot.vaadin.components.mvp.MvpView;

/**
 * Created by maggouh on 15/02/17.
 */
public interface IListView extends MvpView, MvpHasPresenterHandlers<ListPresenterHandlers> {
    void initView();

    void refreshData();
}