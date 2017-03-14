package com.springboot.vaadin.ui.report;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.springboot.vaadin.components.mvp.view.MvpView;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by maggouh on 13/03/17.
 */

@Component
public class ReportView extends AbstractMvpView<ReportPresenter> {

    @Autowired
    FirstPdf firstPdf;

    private ReportPresenter reportPresenter;

    private boolean needsBuilding = true;

    Button generateButton = new Button("Generate Pdf");

    @Override
    public MvpView<ReportPresenter> buildView() {
        needsBuilding = true;
        if (needsBuilding) {
            needsBuilding = false;

            final VerticalLayout layout = new VerticalLayout();
            layout.setSizeFull();
            layout.setMargin(true);

            generateButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    firstPdf.buildPdf();
                }
            });

            layout.addComponent(generateButton);
            setCompositionRoot(layout);
        }
        return this;
    }

    @Override
    public void setPresenter(ReportPresenter reportPresenter) {
        this.reportPresenter = reportPresenter;
    }
}
