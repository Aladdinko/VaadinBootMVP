package com.springboot.vaadin.components.mvp.presenter;

import com.vaadin.ui.Component;

import java.io.Serializable;

/**
 * Created by maggouh on 16/02/17.
 */
public interface MvpPresenter extends Serializable {
    public Component getViewComponent();
}