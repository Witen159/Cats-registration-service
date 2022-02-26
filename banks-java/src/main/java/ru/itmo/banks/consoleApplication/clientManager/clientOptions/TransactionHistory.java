package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.client.Client;
import ru.itmo.banks.entity.transaction.AbstractTransaction;

public class TransactionHistory implements ClientOption {
    public void option(Client currentClient) {
        for (AbstractAccount account : currentClient.getAccounts()) {
            System.out.println("Account " + account.getId() + " transactions id:");

            for (AbstractTransaction transaction : account.getTransactionHistory()) {
                System.out.println("\t" + transaction.getId());
            }
        }
    }
}
