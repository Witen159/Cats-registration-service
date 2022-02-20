package banks.classes.transaction;

import banks.classes.account.AccountTemplate;

import java.time.LocalDateTime;

public class AbstractTransaction {
    private static int currentId = 1;
    protected AccountTemplate sender;
    protected AccountTemplate recipient;
    protected double amountOfMoney;
    protected LocalDateTime transactionTime;
    protected int id;
    protected boolean canceled;

    public AbstractTransaction(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney, LocalDateTime currentTime) {
        id = currentId++;
        this.sender = sender;
        this.recipient = recipient;
        this.amountOfMoney = amountOfMoney;
        transactionTime = currentTime;
        canceled = false;
    }

    public AccountTemplate getSender() {
        return sender;
    }

    public AccountTemplate getRecipient() {
        return recipient;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public int getId() {
        return id;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
