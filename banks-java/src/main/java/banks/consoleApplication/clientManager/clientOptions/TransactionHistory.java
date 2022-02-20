package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.account.AbstractAccount;
import banks.classes.client.Client;
import banks.classes.transaction.AbstractTransaction;

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
