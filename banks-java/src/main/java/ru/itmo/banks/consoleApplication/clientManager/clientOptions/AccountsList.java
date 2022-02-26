package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.client.Client;

public class AccountsList implements ClientOption {
    public void option(Client currentClient) {
        System.out.println("Your accounts id:");
        for (AbstractAccount account : currentClient.getAccounts()) {
            System.out.println(account.getId());
        }
    }
}
