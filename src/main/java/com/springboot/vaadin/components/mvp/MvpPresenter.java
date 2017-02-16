package com.springboot.vaadin.components.mvp;

import com.vaadin.ui.Component;

import java.io.Serializable;

/**
 * Created by maggouh on 16/02/17.
 */
public interface MvpPresenter extends Serializable {

    public void postConstruct();

    public void preDestroy();

    public Component getViewComponent();

}