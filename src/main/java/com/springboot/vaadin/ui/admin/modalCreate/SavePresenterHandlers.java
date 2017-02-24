package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.MvpPresenterHandlers;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;
import com.springboot.vaadin.domain.Role;

import java.util.Set;

/**
 * Created by maggouh on 21/02/17.
 */
public interface SavePresenterHandlers extends MvpPresenterHandlers {

    Set<Role> getAllRole();

    void SaveAccount(String username, String passwordValue, Set<Role> roles) throws UsernameAlreadyUsedException;

}
