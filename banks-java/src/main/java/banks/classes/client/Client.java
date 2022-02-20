package banks.classes.client;

import banks.classes.account.AccountTemplate;
import banks.classes.observer.IObserver;
import banks.classes.observer.notification.INotification;
import banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements IObserver {
    private final ArrayList<AccountTemplate> accounts = new ArrayList<AccountTemplate>();
    private final ArrayList<INotification> notifications = new ArrayList<INotification>();
    private String name;
    private String surname;
    private String address = null;
    private int passportNumber = 0;
    private boolean verification;
    private int id;

    public List<AccountTemplate> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public List<INotification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public void addAccount(AccountTemplate newAccount) {
        accounts.add(newAccount);
    }

    public void setAddress(String address) {
        this.address = address;
        verificationCheck();
    }

    public void setPassport(int passportNumber) throws BankException {
        if (getPassportNumber() != 0)
            throw new BankException("Passport number already added");
        this.passportNumber = passportNumber;
        verificationCheck();
    }

    public void update(INotification notification) {
        notifications.add(notification);
    }

    private void verificationCheck() {
        setVerification(getAddress() != null && getPassportNumber() != 0);
        if (isVerification()) {
            for (AccountTemplate account : accounts) {
                account.сonfirmVerification();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public boolean isVerification() {
        return verification;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public void setId(int id) {
        this.id = id;
    }
}
