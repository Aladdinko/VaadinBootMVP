package com.springboot.vaadin;

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


    }

}
