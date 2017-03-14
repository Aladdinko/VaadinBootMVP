package com.springboot.vaadin.ui.events;

/**
 * Created by maggouh on 21/02/17.
 */
public class AccountEvent {

    private final AccountEventType accountEventType;

    public AccountEvent(AccountEventType accountEventType) {
        this.accountEventType = accountEventType;
    }

    public AccountEventType getAccountEventType() {
        return accountEventType;
    }

    @Override
    public String toString() {
        return "Event : " + accountEventType.toString();
    }
}
