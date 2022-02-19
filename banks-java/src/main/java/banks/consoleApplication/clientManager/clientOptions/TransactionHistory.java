package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.account.AccountTemplate;
import banks.classes.client.Client;
import banks.classes.transaction.AbstractTransaction;

public class TransactionHistory implements IClientOption{
    public void Option(Client currentClient)
    {
        for (AccountTemplate account : currentClient.GetAccounts())
        {
            System.out.println("Account " + account.getId() + " transactions id:");

            for (AbstractTransaction transaction : account.GetTransactionHistory())
            {
                System.out.println("\t" + transaction.getId());
            }
        }
    }
}
