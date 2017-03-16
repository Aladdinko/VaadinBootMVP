package com.springboot.vaadin.components.mvp.presenter;

import com.springboot.vaadin.components.mvp.view.MvpView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by maggouh on 16/02/17.
 */
@SuppressWarnings("serial")
public abstract class AbstractMvpPresenterView<V extends MvpView> implements MvpPresenter, View {

    private final V view;

    public AbstractMvpPresenterView(final V view) {
        this.view =  view;
    }

    public V getView() {
        return view;
    }

    @Override
    public Component getViewComponent() {
        return (Component) getView();
    }

    @PostConstruct
    public void postConstruct() {
    }

    @Override
    public abstract void enter(ViewChangeListener.ViewChangeEvent event);

    @PreDestroy
    public void preDestroy() {
    }


}