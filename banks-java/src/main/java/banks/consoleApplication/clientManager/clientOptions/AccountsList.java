package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.account.AccountTemplate;
import banks.classes.client.Client;

public class AccountsList implements IClientOption{
    public void Option(Client currentClient)
    {
        System.out.println("Your accounts id:");
        for (AccountTemplate account : currentClient.GetAccounts())
        {
            System.out.println(account.getId());
        }
    }
}
