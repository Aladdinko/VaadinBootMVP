package com.springboot.vaadin.ui.admin.modalCreate;

import com.springboot.vaadin.components.mvp.MvpPresenterHandlers;
import com.springboot.vaadin.dao.exception.UsernameAlreadyUsedException;

import java.util.List;

/**
 * Created by maggouh on 21/02/17.
 */
public interface SavePresenterHandlers extends MvpPresenterHandlers {

    List<String> getAllRole();

    void SaveAccount(String username, String passwordValue, String roles) throws UsernameAlreadyUsedException;

}
