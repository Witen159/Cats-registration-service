package ru.itmo.banks.entity.account;

import ru.itmo.banks.entity.transaction.AbstractTransaction;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractAccount {
    private static int currentId = 1;
    protected double money;
    protected ArrayList<AbstractTransaction> transactionHistory = new ArrayList<AbstractTransaction>();
    protected LocalDateTime currentTime;
    protected int id;
    protected boolean verification;

    public AbstractAccount(double startMoney, LocalDateTime currentTime, boolean verification) {
        id = currentId++;
        money = startMoney;
        this.currentTime = currentTime;
        this.verification = verification;
    }

    public void increaseMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        money += amountOfMoney;
    }

    public void reduceMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney < 0)
            throw new BankException("You should change account money only in positive amount of money");
        money -= amountOfMoney;
    }

    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
    }

    public void addTransaction(AbstractTransaction transaction) {
        transactionHistory.add(transaction);
    }

    // Изменение баланса в обход ограничений счета для отмены операций
    public void cancelOperation(double transactionAmount) {
        money += transactionAmount;
    }

    public void сonfirmVerification() {
        verification = true;
    }

    public void transactionCheck(AbstractTransaction transaction) throws BankException {
        if (!transactionHistory.contains(transaction))
            throw new BankException("Transaction" + transaction.getId() + "does not belong to the account" + getId());
    }

    public List<AbstractTransaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public double getMoney() {
        return Math.round(money * 100.0) / 100.0;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public int getId() {
        return id;
    }

    public boolean isVerification() {
        return verification;
    }
}
