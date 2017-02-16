package com.springboot.vaadin.components.mvp;

/**
 * Created by maggouh on 16/02/17.
 */
public interface MvpHasPresenterHandlers<H extends MvpPresenterHandlers> {

    public void setPresenterHandlers(H mvpPresenterHandlers);
}