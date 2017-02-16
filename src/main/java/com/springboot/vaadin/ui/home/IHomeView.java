package com.springboot.vaadin.ui.home;

import com.springboot.vaadin.components.mvp.MvpHasPresenterHandlers;
import com.springboot.vaadin.components.mvp.MvpView;

/**
 * Created by maggouh on 17/02/17.
 */
public interface IHomeView extends MvpView, MvpHasPresenterHandlers<HomePresenterHandlers> {

    public void initView(String userName, String loginType);
}

