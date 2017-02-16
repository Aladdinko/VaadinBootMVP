package com.springboot.vaadin;

import com.springboot.vaadin.ui.ViewToken;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

/**
 * Created by maggouh on 17/02/17.
 */
@Component("vaadinServlet")
public class CustomServlet extends SpringVaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().setSystemMessagesProvider(new SystemMessagesProvider() {
            @Override
            public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
                CustomizedSystemMessages messages = new CustomizedSystemMessages();

                messages.setSessionExpiredNotificationEnabled(true);
                messages.setSessionExpiredURL(ViewToken.HOME);

                return messages;
            }
        });
    }

}
