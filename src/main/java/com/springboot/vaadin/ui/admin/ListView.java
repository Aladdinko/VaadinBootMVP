package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.TableColumnDefinition;
import com.springboot.vaadin.components.TableColumnDefinitionList;
import com.springboot.vaadin.components.mvp.view.AbstractMvpView;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by maggouh on 14/02/17.
 */

@Component
@SuppressWarnings("serial")
public class ListView extends AbstractMvpView implements IListView {

    private ListPresenterHandlers listPresenterHandlers;
    private VerticalLayout layout;


    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    public VerticalLayout getListUsersTable() {

        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();


        Collection<Account> accounts = listPresenterHandlers.getAllAccounts();

        BeanItemContainer<Account> container = new BeanItemContainer<Account>(Account.class, accounts);
        RTLTable table = new RTLTable("debug1");
        table.setContainerDataSource(container);
        table.setCaption("LIST OF USERS ");
        table.addGeneratedColumn("authoritiesList", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                Account account = (Account) itemId;
                Collection<String> rolesString = new ArrayList<String>();
                for (Role role : account.getAuthorities()) {
//                    layout.addComponent(new Label(role.getName()));
                    rolesString.add(role.getAuthority());
                }
                return rolesString;
            }
        });
        table.getColumnAlignment("authoritiesList").toString();
        table.setSelectable(true);
        table.setFooterVisible(true);

        TableColumnDefinitionList columnDefinitionList = new TableColumnDefinitionList();
        columnDefinitionList.add(0, new TableColumnDefinition("username", "Username", Table.Align.LEFT));
        columnDefinitionList.add(1, new TableColumnDefinition("password", "Password", Table.Align.LEFT));
        columnDefinitionList.add(2, new TableColumnDefinition("authoritiesList", "Roles", Table.Align.LEFT));
        table.setColumnsByColumnDefinitionList(columnDefinitionList);
        table.setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button buttonCreate = buttonCreateUsers();
        Button buttonEdit = buttonEditUsers(table, true);
        buttonLayout.addComponent(buttonCreate);
        buttonLayout.addComponent(buttonEdit);

        tableLayout.addComponent(table);

        if (vaadinSharedSecurity.hasAuthority(Role.ROLE_ADMIN)) {
            tableLayout.addComponent(buttonLayout);
        }

        tableLayout.setMargin(true);
        return tableLayout;
    }

    public Button buttonCreateUsers() {
        Button buttonCreateAccounts = new Button("Open Creating Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(listPresenterHandlers.showView().getParent() != null) {
                    Notification.show("The window is already open", Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    getUI().addWindow(listPresenterHandlers.showView());
                }

            }
        });
        buttonCreateAccounts.setStyleName(ValoTheme.BUTTON_PRIMARY);
        return buttonCreateAccounts;
    }

    public Button buttonEditUsers(final RTLTable table, final boolean tag) {
        Button buttonEditAccounts = new Button("Edit Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                listPresenterHandlers.editAccount(table, tag);
            }

        });
        buttonEditAccounts.setIcon(FontAwesome.PENCIL);
        buttonEditAccounts.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        return buttonEditAccounts;
    }


    @Override
    public void setPresenterHandlers(ListPresenterHandlers listPresenterHandlers) {
        this.listPresenterHandlers = listPresenterHandlers;
    }

    @Override
    public void initView() {
        setSizeFull();
        layout = getListUsersTable();
        layout.setSizeFull();
        setCompositionRoot(layout);
    }

}
