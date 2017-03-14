package com.springboot.vaadin.components.mvp.presenter;

import com.springboot.vaadin.components.mvp.view.MvpView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import org.vaadin.spring.events.EventBus;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by maggouh on 16/02/17.
 */
@SuppressWarnings("serial")
public abstract class AbstractMvpPresenterView<V extends MvpView> implements MvpPresenter, View {

    private final V view;
    private final EventBus eventBus;

    public AbstractMvpPresenterView(final V view, final EventBus eventBus) {
        this.view =  view;
        this.eventBus =  eventBus;
    }

    public V getView() {
        return view;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public Component getViewComponent() {
        return (Component) getView();
    }

    @PostConstruct
    public void postConstruct() {
        eventBus.subscribe(this);
    }

    @Override
    public abstract void enter(ViewChangeListener.ViewChangeEvent event);

    @PreDestroy
    public void preDestroy() {
        eventBus.unsubscribe(this);
    }


}