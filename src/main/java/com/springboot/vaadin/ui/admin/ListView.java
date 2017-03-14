package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.springboot.vaadin.components.mvp.view.MvpView;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import java.util.Collection;

/**
 * Created by maggouh on 14/02/17.
 */

@Component
public class ListView extends AbstractMvpView<ListPresenter> {

    private ListPresenter listPresenter;
    private VerticalLayout layout;
    private Table table;
    private boolean needsBuilding = true;
    BeanItemContainer<Account> container;

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;


    public VerticalLayout getListUsersTable() {


        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();
        final Collection<Account> accounts = listPresenter.getAllAccounts();
        container = new BeanItemContainer<Account>(Account.class, accounts);


        table = new Table("List", container);
        table.setCaption("LIST OF USERS ");
        table.setSelectable(true);

        table.addGeneratedColumn("authoritiesList", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                VerticalLayout verticalLayout = new VerticalLayout();
                Account account = (Account) itemId;
                for (Role role : account.getAuthorities()) {
                    verticalLayout.addComponent(new Label(role.toString()));
                }
                return verticalLayout;
            }
        });
        table.addGeneratedColumn("edit", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, final Object itemId, Object columnId) {
                HorizontalLayout editLayout = new HorizontalLayout();

                final Account account = (Account) itemId;
                final Button button = new Button(FontAwesome.EDIT);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        listPresenter.openWindowEdition(account);
                    }
                });
                editLayout.addComponent(button);

                return editLayout;
            }
        });

        if (vaadinSharedSecurity.hasAuthority(Role.ROLE_ADMIN)) {
            table.setVisibleColumns(new Object[]{"username", "password", "authoritiesList", "edit"});
            table.setColumnHeaders("Username", "Password", "Roles", "Edit");
        } else {
            table.setVisibleColumns(new Object[]{"username", "password", "authoritiesList"});
            table.setColumnHeaders("Username", "Password", "Roles");
        }


        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button buttonCreate = buttonCreateUsers();
        buttonLayout.addComponent(buttonCreate);

        tableLayout.addComponent(table);

        if (vaadinSharedSecurity.hasAuthority(Role.ROLE_ADMIN)) {
            tableLayout.addComponent(buttonLayout);
        }

        tableLayout.setMargin(true);

        refreshData();

        return tableLayout;
    }

    public void setModel(Collection<Account> accounts) {
        container = new BeanItemContainer<Account>(Account.class, accounts);
        table.setContainerDataSource(container);
        if (vaadinSharedSecurity.hasAuthority(Role.ROLE_ADMIN)) {
            table.setVisibleColumns(new Object[]{"username", "password", "authoritiesList", "edit"});
        } else {
            table.setVisibleColumns(new Object[]{"username", "password", "authoritiesList"});
        }
    }

    public void refreshData() {
        Collection<Account> accounts = listPresenter.getAllAccounts();
        table.setContainerDataSource(null);
        setModel(accounts);
    }

    public Button buttonCreateUsers() {
        Button buttonCreateAccounts = new Button("Open Creating Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                listPresenter.openWindowCreation();
            }
        });
        buttonCreateAccounts.setStyleName(ValoTheme.BUTTON_PRIMARY);
        return buttonCreateAccounts;
    }

    @Override
    public void setPresenter(ListPresenter listPresenter) {
        this.listPresenter = listPresenter;
    }

    @Override
    public MvpView<ListPresenter> buildView() {
        needsBuilding = true;
        if (needsBuilding) {
            needsBuilding = false;
            setSizeFull();
            layout = getListUsersTable();
            layout.setSizeFull();
            setCompositionRoot(layout);
        }
        return this;
    }
}