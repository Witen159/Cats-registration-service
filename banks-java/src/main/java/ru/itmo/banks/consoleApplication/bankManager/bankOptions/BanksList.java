package ru.itmo.banks.consoleApplication.bankManager.bankOptions;

import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.bank.Bank;

import java.time.LocalDateTime;

public class BanksList implements BankOption {
    public void option() {
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        for (Bank bank : centralBank.getBanks()) {
            System.out.println(bank.getName() + " id: " + bank.getId());
        }

        System.out.println();
    }
}
