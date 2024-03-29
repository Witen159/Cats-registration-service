package ru.itmo.banks.entity.client;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.observer.Observer;
import ru.itmo.banks.entity.observer.notification.Notification;
import ru.itmo.banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements Observer {
    private final List<AbstractAccount> accounts = new ArrayList<AbstractAccount>();
    private final List<Notification> notifications = new ArrayList<Notification>();
    private String name;
    private String surname;
    private String address = null;
    private int passportNumber = 0;
    private boolean verification;
    private int id;

    public List<AbstractAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public void addAccount(AbstractAccount newAccount) {
        accounts.add(newAccount);
    }

    public void setPassport(int passportNumber) {
        if (getPassportNumber() != 0)
            throw new BankException("Passport number already added");
        this.passportNumber = passportNumber;
        verificationCheck();
    }

    public void update(Notification notification) {
        notifications.add(notification);
    }

    private void verificationCheck() {
        setVerification(getAddress() != null && getPassportNumber() != 0);
        if (isVerification()) {
            for (AbstractAccount account : accounts) {
                account.сonfirmVerification();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        verificationCheck();
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
