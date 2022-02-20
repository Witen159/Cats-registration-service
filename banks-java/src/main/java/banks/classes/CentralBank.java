package banks.classes;

import banks.classes.bank.Bank;
import banks.classes.bank.PercentAmount;
import banks.classes.client.Client;
import banks.classes.transaction.AbstractTransaction;
import banks.classes.transaction.CancelTransaction;
import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentralBank {
    private static CentralBank _instance;
    private ArrayList<Bank> _banks = new ArrayList<>();

    private CentralBank(LocalDateTime currentTime) {
        CurrentTime = currentTime;
    }

    private LocalDateTime CurrentTime;

    public static CentralBank GetInstance(LocalDateTime currentTime) {
        if (_instance == null)
            _instance = new CentralBank(currentTime);
        return _instance;
    }

    public Bank RegisterNewBank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission) throws BankException {
        var newBank = new Bank(name, operationLimit, creditNegativeLimit, depositInterestOnTheBalance, debitInterestOnTheBalance, commission, CurrentTime);
        _banks.add(newBank);
        return newBank;
    }

    public LocalDateTime GetCurrentTime() {
        return _instance.CurrentTime;
    }

    public void NewDate(LocalDateTime newDate) throws BankException {
        if (Duration.between(CurrentTime, newDate).toDays() < 1)
            throw new BankException("New date should be at least next day");
        _instance.CurrentTime = newDate;
        PaymentOperation();
    }

    public Bank GetBank(int bankId) {
        for (Bank bank : _banks)
            if (bank.getId() == bankId)
                return bank;
        return null;
    }

    public AbstractTransaction CancelOperation(AbstractTransaction transaction) throws BankException {
        return new CancelTransaction(transaction);
    }

    private void PaymentOperation() throws BankException {
        for (Bank bank : _banks) {
            bank.PaymentOperation(CurrentTime);
        }
    }

    public List<Bank> GetBanks() {
        return Collections.unmodifiableList(_banks);
    }
}
