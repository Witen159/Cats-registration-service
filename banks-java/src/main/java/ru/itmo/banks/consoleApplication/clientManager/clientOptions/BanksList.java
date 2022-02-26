package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.bank.Bank;
import ru.itmo.banks.entity.client.Client;

import java.time.LocalDateTime;

public class BanksList implements ClientOption {
    public void option(Client currentClient) {
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        for (Bank bank : centralBank.getBanks()) {
            System.out.println(bank.getName() + " id: " + bank.getId());
            if (bank.getClients().contains(currentClient))
                System.out.println("(You registered)");
            System.out.println();
        }
    }
}
