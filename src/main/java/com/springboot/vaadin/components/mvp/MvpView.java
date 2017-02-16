package com.springboot.vaadin.components.mvp;

import java.io.Serializable;

/**
 * Created by maggouh on 16/02/17.
 */
public interface MvpView extends Serializable {

    public void postConstruct();

    public void preDestroy();

}