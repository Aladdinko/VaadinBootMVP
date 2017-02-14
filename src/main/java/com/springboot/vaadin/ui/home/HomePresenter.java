package com.springboot.vaadin.ui.home;


import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenterView;

/**
 * Created by maggouh on 13/02/17.
 */

@SpringView(name= ViewToken.HOME)
public class HomePresenter extends AbstractMvpPresenterView<HomePresenter.HomeView> implements HomePresenterHandlers {

    public interface HomeView extends MvpView, MvpHasPresenterHandlers<HomePresenterHandlers> {
        public void initView(String userName, String loginType);
    }

    @Autowired
    public HomePresenter(HomeView view, EventBus.ViewEventBus eventBus) {
        super(view, eventBus);
        getView().setPresenterHandlers(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

		/*
		 * UsernamePasswordAuthenticationToken OR
		 * AnonymousAuthenticationToken
		 */

//        Authentication authentication = security.getAuthentication();
//        getView().initView(authentication.getName(), authentication.getClass().getSimpleName());

    }

}
