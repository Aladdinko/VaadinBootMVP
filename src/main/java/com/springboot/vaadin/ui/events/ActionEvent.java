package com.springboot.vaadin.ui.events;

/**
 * Created by maggouh on 15/03/17.
 */
public class ActionEvent {
    private final ActionEventType actionEventType;

    public ActionEvent(ActionEventType actionEventType) {
        this.actionEventType = actionEventType;
    }

    public ActionEventType getActionEventType() {
        return actionEventType;
    }

    @Override
    public String toString() {
        return "Event : " + actionEventType.toString();
    }
}