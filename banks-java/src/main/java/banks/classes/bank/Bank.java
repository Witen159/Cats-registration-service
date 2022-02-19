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
    private ArrayList<AccountTemplate> _accounts = new ArrayList<AccountTemplate>();
    private ArrayList<Client> _clients = new ArrayList<Client>();
    private ArrayList<IObserver> _observers = new ArrayList<IObserver>();
    private LocalDateTime _currentTime;
    private int Id;

    private String Name;
    private BankParametersChanger BankParametersChanger;
    private double DebitInterestOnTheBalance;
    private double Commission;
    private int OperationLimit;
    private int CreditNegativeLimit;
    private PercentAmount DepositInterestOnTheBalance;

    public Bank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission, LocalDateTime currentTime) throws BankException {
        Name = name;
        Id = _currentId++;
        BankParametersChanger = new BankParametersChanger(this);
        BankParametersChanger.ChangeOperationLimit(operationLimit);
        BankParametersChanger.ChangeCreditNegativeLimit(creditNegativeLimit);
        BankParametersChanger.ChangeDepositInterestOnTheBalance(depositInterestOnTheBalance);
        BankParametersChanger.ChangeDebitInterestOnTheBalance(debitInterestOnTheBalance);
        BankParametersChanger.ChangeCommission(commission);
        _currentTime = currentTime;
    }

    // public IReadOnlyList<AccountTemplate> Accounts =>_accounts;
    // public IReadOnlyList<Client> Clients =>_clients;

    public AccountTemplate AddDebitAccount(Client client, double startMoney) throws BankException {
        ClientRegisterCheck(client);
        var debitAccount = new DebitAccount(startMoney, _currentTime, getDebitInterestOnTheBalance(), client.isVerification());
        client.AddAccount(debitAccount);
        _accounts.add(debitAccount);
        return debitAccount;
    }

    public AccountTemplate AddDepositAccount(Client client, double startMoney, LocalDateTime depositCloseTime) throws BankException {
        ClientRegisterCheck(client);
        var depositAccount = new DepositAccount(startMoney, _currentTime, getDepositInterestOnTheBalance().GetCurrentPercent(startMoney), depositCloseTime, client.isVerification());
        client.AddAccount(depositAccount);
        _accounts.add(depositAccount);
        return depositAccount;
    }

    public AccountTemplate AddCreditAccount(Client client, double startMoney) throws BankException {
        ClientRegisterCheck(client);
        var creditAccount = new CreditAccount(startMoney, _currentTime, getCommission(), getCreditNegativeLimit(), client.isVerification());
        client.AddAccount(creditAccount);
        _accounts.add(creditAccount);
        return creditAccount;
    }

    public AbstractTransaction Transfer(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney) throws BankException {
        AccountCheck(sender);
        OperationLimitCheck(sender, amountOfMoney);
        return new TransferTransaction(sender, recipient, amountOfMoney, _currentTime);
    }

    public AbstractTransaction Refill(AccountTemplate account, double amountOfMoney) throws BankException {
        AccountCheck(account);
        return new RefillTransaction(null, account, amountOfMoney, _currentTime);
    }

    public AbstractTransaction Withdrawal(AccountTemplate account, double amountOfMoney) throws BankException {
        AccountCheck(account);
        OperationLimitCheck(account, amountOfMoney);
        return new WithdrawalTransaction(account, null, amountOfMoney, _currentTime);
    }

    public void RegisterNewClient(Client client) {
        _clients.add(client);
    }

    public AccountTemplate GetAccount(int accountId) {
        for (AccountTemplate account : _accounts)
            if (account.getId() == accountId)
                return account;
        return null;
    }

    public void PaymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        _currentTime = timeOfTheNewPayment;
         for(AccountTemplate account : _accounts)
        {
            account.PaymentOperation(_currentTime);
        }
    }

    public void AddObserver(IObserver observer) {
        _observers.add(observer);
    }

    public void RemoveObserver(IObserver observer) {
        _observers.remove(observer);
    }

    public void NotifyObservers(INotification notification) {
        for(IObserver observer : _observers)
        {
            observer.Update(notification);
        }
    }

    private void OperationLimitCheck(AccountTemplate account, double amountOfMoney) throws BankException {
        if (!account.isVerification() && amountOfMoney > getOperationLimit())
            throw new BankException("Unconfirmed accounts are prohibited from operations above" + getOperationLimit());
    }

    private void ClientRegisterCheck(Client client) throws BankException {
        if (!_clients.contains(client))
            throw new BankException("Client should be registered in the bank");
    }

    private void AccountCheck(AccountTemplate account) throws BankException {
        if (!_accounts.contains(account))
            throw new BankException("The account does not belong to this bank");
    }

    public List<Client> GetClients() {
        return Collections.unmodifiableList(_clients);
    }

    public List<AccountTemplate> GetAccounts() {
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
