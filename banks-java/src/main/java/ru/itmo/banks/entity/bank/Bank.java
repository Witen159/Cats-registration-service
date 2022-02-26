package ru.itmo.banks.entity.bank;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.account.CreditAccount;
import ru.itmo.banks.entity.account.DebitAccount;
import ru.itmo.banks.entity.account.DepositAccount;
import ru.itmo.banks.entity.client.Client;
import ru.itmo.banks.entity.observer.Observable;
import ru.itmo.banks.entity.observer.Observer;
import ru.itmo.banks.entity.observer.notification.Notification;
import ru.itmo.banks.entity.transaction.AbstractTransaction;
import ru.itmo.banks.entity.transaction.RefillTransaction;
import ru.itmo.banks.entity.transaction.TransferTransaction;
import ru.itmo.banks.entity.transaction.WithdrawalTransaction;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank implements Observable {
    private static int currentId = 1;
    private final List<AbstractAccount> accounts = new ArrayList<AbstractAccount>();
    private final List<Client> clients = new ArrayList<Client>();
    private final List<Observer> observers = new ArrayList<Observer>();
    private final int id;
    private final String name;
    private final BankParametersChanger bankParametersChanger;
    private LocalDateTime currentTime;
    private double debitInterestOnTheBalance;
    private double commission;
    private int operationLimit;
    private int creditNegativeLimit;
    private PercentAmount depositInterestOnTheBalance;

    public Bank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission, LocalDateTime currentTime) {
        this.name = name;
        id = currentId++;
        bankParametersChanger = new BankParametersChanger(this);
        bankParametersChanger.changeOperationLimit(operationLimit);
        bankParametersChanger.changeCreditNegativeLimit(creditNegativeLimit);
        bankParametersChanger.changeDepositInterestOnTheBalance(depositInterestOnTheBalance);
        bankParametersChanger.changeDebitInterestOnTheBalance(debitInterestOnTheBalance);
        bankParametersChanger.changeCommission(commission);
        this.currentTime = currentTime;
    }

    public AbstractAccount addDebitAccount(Client client, double startMoney) {
        clientRegisterCheck(client);
        var debitAccount = new DebitAccount(startMoney, currentTime, getDebitInterestOnTheBalance(), client.isVerification());
        client.addAccount(debitAccount);
        accounts.add(debitAccount);
        return debitAccount;
    }

    public AbstractAccount addDepositAccount(Client client, double startMoney, LocalDateTime depositCloseTime) {
        clientRegisterCheck(client);
        var depositAccount = new DepositAccount(startMoney, currentTime, getDepositInterestOnTheBalance().getCurrentPercent(startMoney), depositCloseTime, client.isVerification());
        client.addAccount(depositAccount);
        accounts.add(depositAccount);
        return depositAccount;
    }

    public AbstractAccount addCreditAccount(Client client, double startMoney) {
        clientRegisterCheck(client);
        var creditAccount = new CreditAccount(startMoney, currentTime, getCommission(), getCreditNegativeLimit(), client.isVerification());
        client.addAccount(creditAccount);
        accounts.add(creditAccount);
        return creditAccount;
    }

    public AbstractTransaction transfer(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney) {
        accountCheck(sender);
        operationLimitCheck(sender, amountOfMoney);
        return new TransferTransaction(sender, recipient, amountOfMoney, currentTime);
    }

    public AbstractTransaction refill(AbstractAccount account, double amountOfMoney) {
        accountCheck(account);
        return new RefillTransaction(null, account, amountOfMoney, currentTime);
    }

    public AbstractTransaction withdrawal(AbstractAccount account, double amountOfMoney) {
        accountCheck(account);
        operationLimitCheck(account, amountOfMoney);
        return new WithdrawalTransaction(account, null, amountOfMoney, currentTime);
    }

    public void registerNewClient(Client client) {
        clients.add(client);
    }

    public AbstractAccount getAccount(int accountId) {
        for (AbstractAccount account : accounts)
            if (account.getId() == accountId)
                return account;
        return null;
    }

    public void paymentOperation(LocalDateTime timeOfTheNewPayment) {
        currentTime = timeOfTheNewPayment;
        for (AbstractAccount account : accounts) {
            account.paymentOperation(currentTime);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    private void operationLimitCheck(AbstractAccount account, double amountOfMoney) {
        if (!account.isVerification() && amountOfMoney > getOperationLimit())
            throw new BankException("Unconfirmed accounts are prohibited from operations above" + getOperationLimit());
    }

    private void clientRegisterCheck(Client client) {
        if (!clients.contains(client))
            throw new BankException("Client should be registered in the bank");
    }

    private void accountCheck(AbstractAccount account) {
        if (!accounts.contains(account))
            throw new BankException("The account does not belong to this bank");
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public List<AbstractAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public double getDebitInterestOnTheBalance() {
        return debitInterestOnTheBalance;
    }

    public void setDebitInterestOnTheBalance(double debitInterestOnTheBalance) {
        this.debitInterestOnTheBalance = debitInterestOnTheBalance;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getOperationLimit() {
        return operationLimit;
    }

    public void setOperationLimit(int operationLimit) {
        this.operationLimit = operationLimit;
    }

    public int getCreditNegativeLimit() {
        return creditNegativeLimit;
    }

    public void setCreditNegativeLimit(int creditNegativeLimit) {
        this.creditNegativeLimit = creditNegativeLimit;
    }

    public PercentAmount getDepositInterestOnTheBalance() {
        return depositInterestOnTheBalance;
    }

    public void setDepositInterestOnTheBalance(PercentAmount depositInterestOnTheBalance) {
        this.depositInterestOnTheBalance = depositInterestOnTheBalance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BankParametersChanger getBankParametersChanger() {
        return bankParametersChanger;
    }
}
