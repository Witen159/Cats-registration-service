package banks.classes.bank;

import banks.classes.account.AccountTemplate;
import banks.classes.account.CreditAccount;
import banks.classes.account.DebitAccount;
import banks.classes.account.DepositAccount;
import banks.classes.client.Client;
import banks.classes.observer.IObservable;
import banks.classes.observer.IObserver;
import banks.classes.observer.notification.INotification;
import banks.classes.transaction.AbstractTransaction;
import banks.classes.transaction.RefillTransaction;
import banks.classes.transaction.TransferTransaction;
import banks.classes.transaction.WithdrawalTransaction;
import banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank implements IObservable {
    private static int currentId = 1;
    private final ArrayList<AccountTemplate> accounts = new ArrayList<AccountTemplate>();
    private final ArrayList<Client> clients = new ArrayList<Client>();
    private final ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private final int id;
    private final String name;
    private final BankParametersChanger bankParametersChanger;
    private LocalDateTime currentTime;
    private double debitInterestOnTheBalance;
    private double commission;
    private int operationLimit;
    private int creditNegativeLimit;
    private PercentAmount depositInterestOnTheBalance;

    public Bank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission, LocalDateTime currentTime) throws BankException {
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

    public AccountTemplate addDebitAccount(Client client, double startMoney) throws BankException {
        clientRegisterCheck(client);
        var debitAccount = new DebitAccount(startMoney, currentTime, getDebitInterestOnTheBalance(), client.isVerification());
        client.addAccount(debitAccount);
        accounts.add(debitAccount);
        return debitAccount;
    }

    public AccountTemplate addDepositAccount(Client client, double startMoney, LocalDateTime depositCloseTime) throws BankException {
        clientRegisterCheck(client);
        var depositAccount = new DepositAccount(startMoney, currentTime, getDepositInterestOnTheBalance().getCurrentPercent(startMoney), depositCloseTime, client.isVerification());
        client.addAccount(depositAccount);
        accounts.add(depositAccount);
        return depositAccount;
    }

    public AccountTemplate addCreditAccount(Client client, double startMoney) throws BankException {
        clientRegisterCheck(client);
        var creditAccount = new CreditAccount(startMoney, currentTime, getCommission(), getCreditNegativeLimit(), client.isVerification());
        client.addAccount(creditAccount);
        accounts.add(creditAccount);
        return creditAccount;
    }

    public AbstractTransaction transfer(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney) throws BankException {
        accountCheck(sender);
        operationLimitCheck(sender, amountOfMoney);
        return new TransferTransaction(sender, recipient, amountOfMoney, currentTime);
    }

    public AbstractTransaction refill(AccountTemplate account, double amountOfMoney) throws BankException {
        accountCheck(account);
        return new RefillTransaction(null, account, amountOfMoney, currentTime);
    }

    public AbstractTransaction withdrawal(AccountTemplate account, double amountOfMoney) throws BankException {
        accountCheck(account);
        operationLimitCheck(account, amountOfMoney);
        return new WithdrawalTransaction(account, null, amountOfMoney, currentTime);
    }

    public void registerNewClient(Client client) {
        clients.add(client);
    }

    public AccountTemplate getAccount(int accountId) {
        for (AccountTemplate account : accounts)
            if (account.getId() == accountId)
                return account;
        return null;
    }

    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        currentTime = timeOfTheNewPayment;
        for (AccountTemplate account : accounts) {
            account.paymentOperation(currentTime);
        }
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(INotification notification) {
        for (IObserver observer : observers) {
            observer.update(notification);
        }
    }

    private void operationLimitCheck(AccountTemplate account, double amountOfMoney) throws BankException {
        if (!account.isVerification() && amountOfMoney > getOperationLimit())
            throw new BankException("Unconfirmed accounts are prohibited from operations above" + getOperationLimit());
    }

    private void clientRegisterCheck(Client client) throws BankException {
        if (!clients.contains(client))
            throw new BankException("Client should be registered in the bank");
    }

    private void accountCheck(AccountTemplate account) throws BankException {
        if (!accounts.contains(account))
            throw new BankException("The account does not belong to this bank");
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public List<AccountTemplate> getAccounts() {
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

    public banks.classes.bank.BankParametersChanger getBankParametersChanger() {
        return bankParametersChanger;
    }
}
