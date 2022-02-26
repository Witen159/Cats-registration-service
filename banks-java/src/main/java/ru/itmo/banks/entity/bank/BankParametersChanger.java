package ru.itmo.banks.entity.bank;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.account.CreditAccount;
import ru.itmo.banks.entity.account.DebitAccount;
import ru.itmo.banks.entity.observer.notification.CommissionNotification;
import ru.itmo.banks.entity.observer.notification.CreditLimitNotification;
import ru.itmo.banks.entity.observer.notification.OperationLimitNotification;
import ru.itmo.banks.entity.observer.notification.PercentNotification;
import ru.itmo.banks.tools.BankException;

public class BankParametersChanger {
    final private int MINIMUM_OPERATION_LIMIT = 10000;
    final private int MINIMUM_CREDIT_LIMIT = 10000;
    final private int MINIMUM_COMMISSION = 1000;
    private final Bank bank;

    public BankParametersChanger(Bank bank) {
        this.bank = bank;
    }

    public void changeOperationLimit(int value)  {
        if (value < MINIMUM_OPERATION_LIMIT)
            throw new BankException("Operation limit should be at least 10000");
        bank.setOperationLimit(value);

        bank.notifyObservers(new OperationLimitNotification());
    }

    public void changeCreditNegativeLimit(int value)  {
        if (value < MINIMUM_CREDIT_LIMIT)
            throw new BankException("Credit Negative Limit should be at least 10000");
        bank.setCreditNegativeLimit(value);
        for (AbstractAccount account : bank.getAccounts()) {
            if (account instanceof CreditAccount creditAccount)
                creditAccount.setCreditNegativeLimit(value);
        }

        bank.notifyObservers(new CreditLimitNotification());
    }

    public void changeCommission(double value)  {
        if (value < MINIMUM_COMMISSION)
            throw new BankException("Commission should be at least 10000");
        bank.setCommission(value);
        for (AbstractAccount account : bank.getAccounts()) {
            if (account instanceof CreditAccount creditAccount)
                creditAccount.setCommission(value);
        }

        bank.notifyObservers(new CommissionNotification());
    }

    public void changeDebitInterestOnTheBalance(double value)  {
        if (value <= 0 || value >= 100) {
            throw new BankException("Percents must be greater than 0 and less than 100");
        }

        bank.setDebitInterestOnTheBalance(value);
        for (AbstractAccount account : bank.getAccounts()) {
            if (account instanceof DebitAccount debitAccount)
                debitAccount.setInterestOnTheBalance(value);
        }

        bank.notifyObservers(new PercentNotification());
    }

    public void changeDepositInterestOnTheBalance(PercentAmount newPercentAmount) {
        bank.setDepositInterestOnTheBalance(newPercentAmount);
    }
}
