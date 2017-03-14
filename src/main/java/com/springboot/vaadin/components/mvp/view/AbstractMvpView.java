package com.springboot.vaadin.components.mvp.view;

import com.vaadin.ui.CustomComponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
* Created by maggouh on 16/02/17.
*/
public abstract class AbstractMvpView<P> extends CustomComponent implements MvpView<P> {

    @PostConstruct
    @Override
    public void postConstruct(){

    }

    @PreDestroy
    @Override
    public void preDestroy() {

    }

    @Override
    public void setPresenter(P presenter) {
    }


}