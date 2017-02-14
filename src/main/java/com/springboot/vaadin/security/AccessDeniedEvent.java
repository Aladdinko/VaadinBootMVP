package com.springboot.vaadin.security;

import java.io.Serializable;

/**
 * Created by maggouh on 13/02/17.
 */
public class AccessDeniedEvent implements Serializable {

    private final Throwable cause;
    private final String viewToken;

    public AccessDeniedEvent(Throwable cause, String viewToken) {
        this.cause = cause;
        this.viewToken = viewToken;
    }

    public AccessDeniedEvent(Throwable cause) {
        this(cause, null);
    }

    public AccessDeniedEvent(String viewToken) {
        this(null, viewToken);
    }

    public AccessDeniedEvent() {
        this(null, null);
    }

    public Throwable getCause() {
        return cause;
    }

    public String getViewToken() {
        return viewToken;
    }

}