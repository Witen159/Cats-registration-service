package banks.classes.account;

import banks.classes.transaction.AbstractTransaction;
import banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountTemplate {
    private static int _currentId = 1;
    protected double _money;
    protected ArrayList<AbstractTransaction> _transactionHistory = new ArrayList<AbstractTransaction>();
    protected LocalDateTime CurrentTime;
    protected int Id;
    protected boolean Verification;

    public AccountTemplate(double startMoney, LocalDateTime currentTime, boolean verification) {
        Id = _currentId++;
        _money = startMoney;
        this.CurrentTime = currentTime;
        Verification = verification;
    }

    public void increaseMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        _money += amountOfMoney;
    }

    public void reduceMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        _money -= amountOfMoney;
    }

    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
    }

    public void addTransaction(AbstractTransaction transaction) {
        _transactionHistory.add(transaction);
    }

    // Изменение баланса в обход ограничений счета для отмены операций
    public void cancelOperation(double transactionAmount) {
        _money += transactionAmount;
    }

    public void сonfirmVerification() {
        Verification = true;
    }

    public void transactionCheck(AbstractTransaction transaction) throws BankException {
        if (!_transactionHistory.contains(transaction))
            throw new BankException("Transaction" + transaction.getId() + "does not belong to the account" + getId());
    }

    public List<AbstractTransaction> getTransactionHistory() {
        return Collections.unmodifiableList(_transactionHistory);
    }

    public double getMoney() {
        return Math.round(_money * 100.0) / 100.0;
    }

    public void setMoney(double money) {
        _money = money;
    }

    public LocalDateTime getCurrentTime() {
        return CurrentTime;
    }

    public int getId() {
        return Id;
    }

    public boolean isVerification() {
        return Verification;
    }
}
