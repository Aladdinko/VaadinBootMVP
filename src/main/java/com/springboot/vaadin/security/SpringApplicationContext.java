package com.springboot.vaadin.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.vaadin.spring.events.EventBus;

/**
 * Created by maggouh on 13/02/17.
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static EventBus getEventBus() {
        return ctx.getBean(EventBus.class);
    }

}