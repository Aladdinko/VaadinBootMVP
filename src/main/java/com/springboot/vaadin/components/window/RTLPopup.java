package com.springboot.vaadin.components.window;


import com.springboot.vaadin.components.mvp.view.MvpView;

/**
 * Created by maggouh on 27/02/17.
 */
public abstract class RTLPopup<P> extends RTLWindow implements MvpView<P> {

    public static final String POPUP_STYLE = "popup";

    public RTLPopup() {
        super();
        setStyleName(POPUP_STYLE);
        setClosable(false);
        setModal(true);
        setResizable(false);
        setClosable(true);
        center();

        // setDebugId(this.getClass().getSimpleName());
    }

}
