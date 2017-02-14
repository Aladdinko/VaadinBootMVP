package com.springboot.vaadin.ui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
public interface ViewToken extends Serializable {

    public static final String HOME="";
    public static final String ADMIN="/admin";
    public static final String SIGNIN="/login";

    public static final List<String> VALID_TOKENS = Arrays.asList(new String[]{HOME, ADMIN, SIGNIN});
}