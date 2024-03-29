package ru.itmo.banks.entity;

import ru.itmo.banks.entity.bank.Bank;
import ru.itmo.banks.entity.bank.PercentAmount;
import ru.itmo.banks.entity.transaction.AbstractTransaction;
import ru.itmo.banks.entity.transaction.CancelTransaction;
import ru.itmo.banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentralBank {
    private static CentralBank instance;
    private final List<Bank> banks = new ArrayList<>();
    private LocalDateTime currentTime;

    private CentralBank(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public static CentralBank getInstance(LocalDateTime currentTime) {
        if (instance == null)
            instance = new CentralBank(currentTime);
        return instance;
    }

    public Bank registerNewBank(String name, int operationLimit, int creditNegativeLimit, PercentAmount depositInterestOnTheBalance, double debitInterestOnTheBalance, double commission) {
        var newBank = new Bank(name, operationLimit, creditNegativeLimit, depositInterestOnTheBalance, debitInterestOnTheBalance, commission, currentTime);
        banks.add(newBank);
        return newBank;
    }

    public LocalDateTime getCurrentTime() {
        return instance.currentTime;
    }

    public void newDate(LocalDateTime newDate) {
        if (Duration.between(currentTime, newDate).toDays() < 1)
            throw new BankException("New date should be at least next day");
        instance.currentTime = newDate;
        paymentOperation();
    }

    public Bank getBank(int bankId) {
        for (Bank bank : banks)
            if (bank.getId() == bankId)
                return bank;
        return null;
    }

    public AbstractTransaction cancelOperation(AbstractTransaction transaction) {
        return new CancelTransaction(transaction);
    }

    private void paymentOperation() {
        for (Bank bank : banks) {
            bank.paymentOperation(currentTime);
        }
    }

    public List<Bank> getBanks() {
        return Collections.unmodifiableList(banks);
    }
}
