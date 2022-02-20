package banks.classes.bank;

import banks.classes.account.AccountTemplate;
import banks.classes.account.CreditAccount;
import banks.classes.account.DebitAccount;
import banks.classes.observer.notification.CommissionNotification;
import banks.classes.observer.notification.CreditLimitNotification;
import banks.classes.observer.notification.OperationLimitNotification;
import banks.classes.observer.notification.PercentNotification;
import banks.tools.BankException;

public class BankParametersChanger {
    final private int MINIMUM_OPERATION_LIMIT = 10000;
    final private int MINIMUM_CREDIT_LIMIT = 10000;
    final private int MINIMUM_COMMISSION = 1000;
    private final Bank _bank;

    public BankParametersChanger(Bank bank) {
        _bank = bank;
    }

    public void changeOperationLimit(int value) throws BankException {
        if (value < MINIMUM_OPERATION_LIMIT)
            throw new BankException("Operation limit should be at least 10000");
        _bank.setOperationLimit(value);

        _bank.notifyObservers(new OperationLimitNotification());
    }

    public void changeCreditNegativeLimit(int value) throws BankException {
        if (value < MINIMUM_CREDIT_LIMIT)
            throw new BankException("Credit Negative Limit should be at least 10000");
        _bank.setCreditNegativeLimit(value);
        for (AccountTemplate account : _bank.getAccounts()) {
            if (account instanceof CreditAccount creditAccount)
                creditAccount.setCreditNegativeLimit(value);
        }

        _bank.notifyObservers(new CreditLimitNotification());
    }

    public void changeCommission(double value) throws BankException {
        if (value < MINIMUM_COMMISSION)
            throw new BankException("Commission should be at least 10000");
        _bank.setCommission(value);
        for (AccountTemplate account : _bank.getAccounts()) {
            if (account instanceof CreditAccount creditAccount)
                creditAccount.setCommission(value);
        }

        _bank.notifyObservers(new CommissionNotification());
    }

    public void changeDebitInterestOnTheBalance(double value) throws BankException {
        if (value <= 0 || value >= 100) {
            throw new BankException("Percents must be greater than 0 and less than 100");
        }

        _bank.setDebitInterestOnTheBalance(value);
        for (AccountTemplate account : _bank.getAccounts()) {
            if (account instanceof DebitAccount debitAccount)
                debitAccount.setInterestOnTheBalance(value);
        }

        _bank.notifyObservers(new PercentNotification());
    }

    public void changeDepositInterestOnTheBalance(PercentAmount newPercentAmount) {
        _bank.setDepositInterestOnTheBalance(newPercentAmount);
    }
}
