package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.view.MvpView;
import com.springboot.vaadin.components.window.RTLPopup;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by maggouh on 21/02/17.
 */
@ViewScope
@Component
public class SaveView extends RTLPopup<SavePresenter> {

    private SavePresenter savePresenter;

    private boolean needsBuilding = true;
    private VerticalLayout popupContent;
    private HorizontalLayout rolesLayout = new HorizontalLayout();
    private TwinColSelect rolesSelect;
    private BeanFieldGroup<Account> fieldGroup;
    private Button saveButton;

    @Override
    public void postConstruct() {

    }

    @Override
    public void preDestroy() {
        this.close();
    }

    @Override
    public void setPresenter(SavePresenter savePresenter) {
        this.savePresenter = savePresenter;
    }

    @Override
    public MvpView<SavePresenter> buildView() {
        if (needsBuilding) {
            needsBuilding = false;
            popupContent = new VerticalLayout();
            saveButton = new Button("Save Account", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    try {
                        fieldGroup.commit();
                        savePresenter.saveAccount();
                    } catch (UsernameAlreadyUsedException e) {
                        e.printStackTrace();
                    } catch (FieldGroup.CommitException e) {
                        Notification.show("Username, password and roles are required", e.getMessage().toString(), Notification.Type.WARNING_MESSAGE);
                    }
                }
            });

            saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            saveButton.setIcon(FontAwesome.PLUS);
            popupContent.setMargin(true);
            popupContent.setCaption("Creation&Edition Account");

            addComponent(popupContent);
        }
        return this;
    }

    public void setModel(BeanItem<Account> accountBeanItem, List<Role> allRoles) {

        fieldGroup = new BeanFieldGroup<Account>(Account.class);
        fieldGroup.setBuffered(true);
        popupContent.removeAllComponents();
        fieldGroup.setItemDataSource(accountBeanItem);

        TextField usernameField = fieldGroup.buildAndBind("Username","username", TextField.class);
        usernameField.setNullRepresentation("");
        usernameField.setRequired(true);
        popupContent.addComponent(usernameField);
        PasswordField passwordField = fieldGroup.buildAndBind("Password","password", PasswordField.class);
        passwordField.setNullRepresentation("");
        passwordField.setRequired(true);
        popupContent.addComponent(passwordField);

        rolesSelect = new TwinColSelect("Roles");
        initTwinColSelect(rolesSelect, allRoles, accountBeanItem.getBean().getAuthorities(), Role.class);
        fieldGroup.bind(rolesSelect, "authorities");

        rolesLayout.removeAllComponents();
        rolesLayout.addComponent(rolesSelect);
        popupContent.addComponent(rolesLayout);

        popupContent.addComponent(saveButton);
    }

    private void initTwinColSelect(TwinColSelect twinColSelect, List allEntities, Set selectedEntities, Class propertyClass) {
        BeanItemContainer roleBeanItemContainer = new BeanItemContainer<Account>(propertyClass);
        roleBeanItemContainer.addAll(allEntities);
        twinColSelect.setContainerDataSource(roleBeanItemContainer);
        twinColSelect.setValue(selectedEntities);
        twinColSelect.setRequired(true);
    }
}
