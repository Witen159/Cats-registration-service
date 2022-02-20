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
    private static int _currentId = 1;
    private final ArrayList<AccountTemplate> _accounts = new ArrayList<AccountTemplate>();
    private final ArrayList<Client> _clients = new ArrayList<Client>();
    private final ArrayList<IObserver> _observers = new ArrayList<IObserver>();
    private final int Id;
    private final String Name;
    private final BankParametersChanger BankParametersChanger;
    private LocalDateTime _currentTime;
    private double DebitInterestOnTheBalance;
    private double Commission;
    private int OperationLimit;
    private int CreditNegativeLimit;
    private PercentAmount DepositInterestOnTheBalance;

    public Bank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission, LocalDateTime currentTime) throws BankException {
        Name = name;
        Id = _currentId++;
        BankParametersChanger = new BankParametersChanger(this);
        BankParametersChanger.changeOperationLimit(operationLimit);
        BankParametersChanger.changeCreditNegativeLimit(creditNegativeLimit);
        BankParametersChanger.changeDepositInterestOnTheBalance(depositInterestOnTheBalance);
        BankParametersChanger.changeDebitInterestOnTheBalance(debitInterestOnTheBalance);
        BankParametersChanger.changeCommission(commission);
        _currentTime = currentTime;
    }

    public AccountTemplate addDebitAccount(Client client, double startMoney) throws BankException {
        clientRegisterCheck(client);
        var debitAccount = new DebitAccount(startMoney, _currentTime, getDebitInterestOnTheBalance(), client.isVerification());
        client.addAccount(debitAccount);
        _accounts.add(debitAccount);
        return debitAccount;
    }

    public AccountTemplate addDepositAccount(Client client, double startMoney, LocalDateTime depositCloseTime) throws BankException {
        clientRegisterCheck(client);
        var depositAccount = new DepositAccount(startMoney, _currentTime, getDepositInterestOnTheBalance().getCurrentPercent(startMoney), depositCloseTime, client.isVerification());
        client.addAccount(depositAccount);
        _accounts.add(depositAccount);
        return depositAccount;
    }

    public AccountTemplate addCreditAccount(Client client, double startMoney) throws BankException {
        clientRegisterCheck(client);
        var creditAccount = new CreditAccount(startMoney, _currentTime, getCommission(), getCreditNegativeLimit(), client.isVerification());
        client.addAccount(creditAccount);
        _accounts.add(creditAccount);
        return creditAccount;
    }

    public AbstractTransaction transfer(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney) throws BankException {
        accountCheck(sender);
        operationLimitCheck(sender, amountOfMoney);
        return new TransferTransaction(sender, recipient, amountOfMoney, _currentTime);
    }

    public AbstractTransaction refill(AccountTemplate account, double amountOfMoney) throws BankException {
        accountCheck(account);
        return new RefillTransaction(null, account, amountOfMoney, _currentTime);
    }

    public AbstractTransaction withdrawal(AccountTemplate account, double amountOfMoney) throws BankException {
        accountCheck(account);
        operationLimitCheck(account, amountOfMoney);
        return new WithdrawalTransaction(account, null, amountOfMoney, _currentTime);
    }

    public void registerNewClient(Client client) {
        _clients.add(client);
    }

    public AccountTemplate getAccount(int accountId) {
        for (AccountTemplate account : _accounts)
            if (account.getId() == accountId)
                return account;
        return null;
    }

    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        _currentTime = timeOfTheNewPayment;
        for (AccountTemplate account : _accounts) {
            account.paymentOperation(_currentTime);
        }
    }

    public void addObserver(IObserver observer) {
        _observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        _observers.remove(observer);
    }

    public void notifyObservers(INotification notification) {
        for (IObserver observer : _observers) {
            observer.update(notification);
        }
    }

    private void operationLimitCheck(AccountTemplate account, double amountOfMoney) throws BankException {
        if (!account.isVerification() && amountOfMoney > getOperationLimit())
            throw new BankException("Unconfirmed accounts are prohibited from operations above" + getOperationLimit());
    }

    private void clientRegisterCheck(Client client) throws BankException {
        if (!_clients.contains(client))
            throw new BankException("Client should be registered in the bank");
    }

    private void accountCheck(AccountTemplate account) throws BankException {
        if (!_accounts.contains(account))
            throw new BankException("The account does not belong to this bank");
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(_clients);
    }

    public List<AccountTemplate> getAccounts() {
        return Collections.unmodifiableList(_accounts);
    }

    public double getDebitInterestOnTheBalance() {
        return DebitInterestOnTheBalance;
    }

    public void setDebitInterestOnTheBalance(double debitInterestOnTheBalance) {
        DebitInterestOnTheBalance = debitInterestOnTheBalance;
    }

    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public int getOperationLimit() {
        return OperationLimit;
    }

    public void setOperationLimit(int operationLimit) {
        OperationLimit = operationLimit;
    }

    public int getCreditNegativeLimit() {
        return CreditNegativeLimit;
    }

    public void setCreditNegativeLimit(int creditNegativeLimit) {
        CreditNegativeLimit = creditNegativeLimit;
    }

    public PercentAmount getDepositInterestOnTheBalance() {
        return DepositInterestOnTheBalance;
    }

    public void setDepositInterestOnTheBalance(PercentAmount depositInterestOnTheBalance) {
        DepositInterestOnTheBalance = depositInterestOnTheBalance;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public banks.classes.bank.BankParametersChanger getBankParametersChanger() {
        return BankParametersChanger;
    }
}
