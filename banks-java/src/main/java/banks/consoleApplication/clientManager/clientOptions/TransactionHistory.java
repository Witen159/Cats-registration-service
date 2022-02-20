package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.account.AccountTemplate;
import banks.classes.client.Client;
import banks.classes.transaction.AbstractTransaction;

public class TransactionHistory implements IClientOption {
    public void option(Client currentClient) {
        for (AccountTemplate account : currentClient.getAccounts()) {
            System.out.println("Account " + account.getId() + " transactions id:");

            for (AbstractTransaction transaction : account.getTransactionHistory()) {
                System.out.println("\t" + transaction.getId());
            }
        }
    }
}
