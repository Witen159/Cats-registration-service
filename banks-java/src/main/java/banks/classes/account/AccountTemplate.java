package banks.classes.account;

import banks.classes.transaction.AbstractTransaction;
import banks.tools.BankException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountTemplate {
    private static int _currentId = 1;
    protected double _money;
    protected ArrayList<AbstractTransaction> _transactionHistory = new ArrayList<AbstractTransaction>();

    public AccountTemplate(double startMoney, LocalDateTime currentTime, boolean verification) {
        Id = _currentId++;
        _money = startMoney;
        this.CurrentTime = currentTime;
        Verification = verification;
    }

    protected LocalDateTime CurrentTime; //getter

    protected int Id; //getter

    protected boolean Verification; //getter

    public void IncreaseMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        _money += amountOfMoney;
    }

    public void ReduceMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        _money -= amountOfMoney;
    }

    public void PaymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
    }

    public void AddTransaction(AbstractTransaction transaction) {
        _transactionHistory.add(transaction);
    }

    // Изменение баланса в обход ограничений счета для отмены операций
    public void CancelOperation(double transactionAmount) {
        _money += transactionAmount;
    }

    public void СonfirmVerification() {
        Verification = true;
    }

    public void TransactionCheck(AbstractTransaction transaction) throws BankException {
        if (!_transactionHistory.contains(transaction))
            throw new BankException("Transaction" + transaction.getId() + "does not belong to the account" + getId());
    }

    public List<AbstractTransaction> GetTransactionHistory() {
        return Collections.unmodifiableList(_transactionHistory);
    }

    public double GetMoney() {
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
