package com.springboot.vaadin.ui.home;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;

/**
 * Created by maggouh on 13/02/17.
 */
@Component
public class HomeView extends AbstractMvpView implements HomePresenter.HomeView  {

    private HomePresenterHandlers mvpPresenterHandlers;

    private VerticalLayout content;

    private Label caption;
    private Label loginInfo;

    @Override
    public void postConstruct() {
        super.postConstruct();

        content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        setCompositionRoot(content);

        caption = new Label("Caption", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H2);
        content.addComponent(caption);

        loginInfo = new Label("Login Info", ContentMode.HTML);
        loginInfo.addStyleName(ValoTheme.LABEL_H2);
        content.addComponent(loginInfo);

    }

    @Override
    public void setPresenterHandlers(HomePresenterHandlers mvpPresenterHandlers) {
        this.mvpPresenterHandlers = mvpPresenterHandlers;

    }

    @Override
    public void initView(String userName, String loginType) {
        caption.setValue("Welcome back " + userName + "!");
        loginInfo.setValue("You are signed in using " + loginType + "!");
    }
}
