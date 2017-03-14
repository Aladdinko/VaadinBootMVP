package com.springboot.vaadin.components.mvp.view;


/**
* Created by maggouh on 16/02/17.
*/
public interface MvpView<P> {
    public void postConstruct();
    public void preDestroy();
    public void setPresenter(P presenter);
    public MvpView<P> buildView();
}