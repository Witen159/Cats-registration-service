package banks.classes.transaction;

import banks.classes.account.AccountTemplate;

import java.time.LocalDateTime;

public class AbstractTransaction {
    private static int _currentId = 1;
    protected AccountTemplate Sender;
    protected AccountTemplate Recipient;
    protected double AmountOfMoney;
    protected LocalDateTime TransactionTime;
    protected int Id;
    protected boolean Canceled;

    public AbstractTransaction(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney, LocalDateTime currentTime) {
        Id = _currentId++;
        Sender = sender;
        Recipient = recipient;
        AmountOfMoney = amountOfMoney;
        TransactionTime = currentTime;
        Canceled = false;
    }


    public AccountTemplate getSender() {
        return Sender;
    }

    public AccountTemplate getRecipient() {
        return Recipient;
    }

    public double getAmountOfMoney() {
        return AmountOfMoney;
    }

    public LocalDateTime getTransactionTime() {
        return TransactionTime;
    }

    public int getId() {
        return Id;
    }

    public boolean isCanceled() {
        return Canceled;
    }
}
