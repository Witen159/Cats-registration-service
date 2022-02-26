package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class AccountBalance implements ClientOption {
    public void option(Client currentClient) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter id of account");
        int balanceAccountId = console.nextInt();
        console.nextLine();
        for (AbstractAccount account : currentClient.getAccounts()) {
            if (account.getId() == balanceAccountId)
                System.out.println("Balance: " + account.getMoney());
            break;
        }
    }
}
