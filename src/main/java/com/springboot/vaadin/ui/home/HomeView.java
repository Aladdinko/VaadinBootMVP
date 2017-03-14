package com.springboot.vaadin.ui.home;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.springboot.vaadin.components.mvp.view.MvpView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;

/**
 * Created by maggouh on 13/02/17.
 */
@Component
public class HomeView extends AbstractMvpView<HomePresenter> {

    private HomePresenter homePresenter;
    private boolean needsBuilding = true;


    private VerticalLayout content;

    private Label loginType;

    private Label caption;


    @Override
    public void postConstruct() {
        content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        setCompositionRoot(content);

        caption = new Label("Welcome", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H2);
        content.addComponent(caption);

    }

    @Override
    public MvpView<HomePresenter> buildView() {
        if(needsBuilding) {
            postConstruct();
        }
        return this;
    }

    public void initView(String userName) {
        caption.setValue("Welcome back " + userName + "!");

    }

    @Override
    public void setPresenter(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

}
