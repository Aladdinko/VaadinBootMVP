package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.MvpHasPresenterHandlers;
import com.springboot.vaadin.components.mvp.MvpView;
import com.vaadin.ui.Window;

/**
 * Created by maggouh on 21/02/17.
 */
public interface ISaveView extends MvpView, MvpHasPresenterHandlers<SavePresenterHandlers> {
    Window initView();
}
