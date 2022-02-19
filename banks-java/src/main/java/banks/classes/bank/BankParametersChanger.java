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
    final private int MinimumOperationLimit = 10000;
    final private int MinimumCreditLimit = 10000;
    final private int MinimumCommission = 1000;
    private Bank _bank;

    public BankParametersChanger(Bank bank) {
        _bank = bank;
    }

    public void ChangeOperationLimit(int value) throws BankException {
        if (value < MinimumOperationLimit)
            throw new BankException("Operation limit should be at least 10000");
        _bank.setOperationLimit(value);

        _bank.NotifyObservers(new OperationLimitNotification());
    }

    public void ChangeCreditNegativeLimit(int value) throws BankException {
        if (value < MinimumCreditLimit)
            throw new BankException("Credit Negative Limit should be at least 10000");
        _bank.setCreditNegativeLimit(value);
        for(AccountTemplate account : _bank.GetAccounts())
        {
            if (account instanceof CreditAccount creditAccount)
            creditAccount.setCreditNegativeLimit(value);
        }

        _bank.NotifyObservers(new CreditLimitNotification());
    }

    public void ChangeCommission(double value) throws BankException {
        if (value < MinimumCommission)
            throw new BankException("Commission should be at least 10000");
        _bank.setCommission(value);
        for(AccountTemplate account : _bank.GetAccounts())
        {
            if (account instanceof CreditAccount creditAccount)
                creditAccount.setCommission(value);
        }

        _bank.NotifyObservers(new CommissionNotification());
    }

    public void ChangeDebitInterestOnTheBalance(double value) throws BankException {
        if (value <= 0 || value >= 100) {
            throw new BankException("Percents must be greater than 0 and less than 100");
        }

        _bank.setDebitInterestOnTheBalance(value);
        for(AccountTemplate account: _bank.GetAccounts())
        {
            if (account instanceof DebitAccount debitAccount)
                debitAccount.setInterestOnTheBalance(value);
        }

        _bank.NotifyObservers(new PercentNotification());
    }

    public void ChangeDepositInterestOnTheBalance(PercentAmount newPercentAmount) {
        _bank.setDepositInterestOnTheBalance(newPercentAmount);
    }
}
