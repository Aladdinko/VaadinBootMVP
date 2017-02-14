package com.springboot.vaadin.ui.admin;

import com.springboot.vaadin.components.RTLTable;
import com.springboot.vaadin.components.TableColumnDefinition;
import com.springboot.vaadin.components.TableColumnDefinitionList;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.domain.Role;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Component;
import org.vaadin.spring.mvp.view.AbstractMvpView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by maggouh on 14/02/17.
 */
@UIScope
@Component
public class AdminView extends AbstractMvpView implements AdminPresenter.AdminView {

    private AdminPresenterHandlers adminPresenterHandlers;

    private VerticalLayout layout;

    @Override
    public void postConstruct() {
        super.postConstruct();
        setSizeFull();
        layout = getListUsersTable();
//        layout = new CssLayout();
        layout.setSizeFull();
        setCompositionRoot(layout);


        final Label label = new Label("This is admin only view");
        label.addStyleName(ValoTheme.LABEL_H2);
        layout.addComponent(label);

    }

    public VerticalLayout getListUsersTable() {

        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();

        Label label = new Label("List of users");
        label.setStyleName(ValoTheme.LABEL_H2);
        Collection<Account> accounts = adminPresenterHandlers.getAllAccounts();

        BeanItemContainer<Account> container = new BeanItemContainer<Account>(Account.class, accounts);
        RTLTable table = new RTLTable("debug1");
        table.setContainerDataSource(container);
        table.setCaption("List of user ");
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

        Window popupWindow = getCreateWindow();

        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button buttonCreate = buttonCreateUsers(popupWindow);
        Button buttonEdit = buttonEditUsers(table, true);
        buttonLayout.addComponent(buttonCreate);
        buttonLayout.addComponent(buttonEdit);

        tableLayout.addComponent(label);
        tableLayout.addComponent(table);
//        if (vaadinSharedSecurity.hasAuthority(Role.ROLE_ADMIN)) {
        tableLayout.addComponent(buttonLayout);
//        }

        tableLayout.setMargin(true);
        return tableLayout;
    }

    private Window getCreateWindow() {

    final VerticalLayout popupContent = new VerticalLayout();
    final Window window = new Window("Create Accout View", popupContent);

    final TextField username = new TextField("Username : ");
    final TextField password = new TextField("Password : ");
    final TwinColSelect roles = new TwinColSelect("Roles :");

        roles.setLeftColumnCaption("Select role(s) :");
        roles.setRightColumnCaption("role(s) selected :");
        roles.setMultiSelect(true);

        List<String> authorities = adminPresenterHandlers.getAllRole();
        BeanItemContainer<String> container = new BeanItemContainer<String>(String.class, authorities);

        roles.setContainerDataSource(container);

        popupContent.addComponent(username);
        popupContent.addComponent(password);
        popupContent.addComponent(roles);

        Button button = new Button("Create Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {

                    adminPresenterHandlers.createAccount(username.getValue(), password.getValue(), roles.getValue().toString());
                    window.close();
                    Page.getCurrent().reload();
                } catch (UsernameAlreadyUsedException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setStyleName(ValoTheme.BUTTON_PRIMARY);
        button.setIcon(FontAwesome.PLUS);
        popupContent.addComponent(button);
        popupContent.setMargin(true);

        return window;
    }

    private Button buttonCreateUsers(final Window window) {

        Button buttonCreateAccounts = new Button("Open Creating Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (window.getParent() != null) {
                    Notification.show("Window is already open");
                } else {
                    getUI().addWindow(window);
                }
            }
        });
        buttonCreateAccounts.setStyleName(ValoTheme.BUTTON_PRIMARY);
        window.center();
        return buttonCreateAccounts;
    }

    private Button buttonEditUsers(final RTLTable table, final boolean tag) {
        Button buttonEditAccounts = new Button("Edit Account", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                adminPresenterHandlers.editAccount(table, tag);
            }

        });
        buttonEditAccounts.setIcon(FontAwesome.PENCIL);
        buttonEditAccounts.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        return buttonEditAccounts;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setPresenterHandlers(AdminPresenterHandlers adminPresenterHandlers) {
        this.adminPresenterHandlers = adminPresenterHandlers;
    }
}
