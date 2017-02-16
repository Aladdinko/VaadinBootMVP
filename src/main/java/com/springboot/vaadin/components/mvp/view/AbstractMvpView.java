package com.springboot.vaadin.components.mvp.view;

import com.springboot.vaadin.components.mvp.MvpView;
import com.vaadin.ui.CustomComponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by maggouh on 16/02/17.
 */
public abstract class AbstractMvpView extends CustomComponent implements MvpView {

    @PostConstruct
    @Override
    public void postConstruct() {

    }

    @PreDestroy
    @Override
    public void preDestroy() {

    }

}