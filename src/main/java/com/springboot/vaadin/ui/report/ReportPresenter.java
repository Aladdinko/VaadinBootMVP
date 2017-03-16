package com.springboot.vaadin.ui.report;

import com.springboot.vaadin.components.mvp.presenter.AbstractMvpPresenterView;
import com.springboot.vaadin.domain.Role;
import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.events.EventBus;

/**
 * Created by maggouh on 13/03/17.
 */
@SpringView(name = ViewToken.PDFGENERATION)
@Secured({Role.ROLE_ADMIN, Role.ROLE_USER, Role.ROLE_TRAINEE})
public class ReportPresenter  extends AbstractMvpPresenterView<ReportView>  {

    @Autowired
    ReportView reportView;

    public ReportPresenter(ReportView view, EventBus.ViewEventBus eventBus) {
        super(view);
        view.setPresenter(this);
    }

    public ReportView getView() {
        reportView.buildView();
        return reportView;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
