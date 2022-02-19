package banks.classes.client;

import banks.classes.account.AccountTemplate;
import banks.classes.observer.IObserver;
import banks.classes.observer.notification.INotification;
import banks.classes.transaction.AbstractTransaction;
import banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements IObserver {
    private ArrayList<AccountTemplate> _accounts = new ArrayList<AccountTemplate>();
    private ArrayList<INotification> _notifications = new ArrayList<INotification>();
    private String Name;
    private String Surname;
    private String Address = null;
    private int PassportNumber = 0;
    private boolean Verification;
    private int Id;

    public List<AccountTemplate> GetAccounts() {
        return Collections.unmodifiableList(_accounts);
    }

    public List<INotification> GetNotifications() {
        return Collections.unmodifiableList(_notifications);
    }

    public void AddAccount(AccountTemplate newAccount) {
        _accounts.add(newAccount);
    }

    public void SetAddress(String address) {
        Address = address;
        VerificationCheck();
    }

    public void SetPassport(int passportNumber) throws BankException {
        if (getPassportNumber() != 0)
            throw new BankException("Passport number already added");
        PassportNumber = passportNumber;
        VerificationCheck();
    }

    public void Update(INotification notification) {
        _notifications.add(notification);
    }

    private void VerificationCheck() {
        setVerification(getAddress() != null && getPassportNumber() != 0);
        if (isVerification()) {
            for(AccountTemplate account : _accounts)
            {
                account.СonfirmVerification();
            }
        }
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getAddress() {
        return Address;
    }

    public int getPassportNumber() {
        return PassportNumber;
    }

    public boolean isVerification() {
        return Verification;
    }

    public int getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setVerification(boolean verification) {
        Verification = verification;
    }

    public void setId(int id) {
        Id = id;
    }
}
