package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.account.AbstractAccount;
import banks.classes.client.Client;

public class AccountsList implements ClientOption {
    public void option(Client currentClient) {
        System.out.println("Your accounts id:");
        for (AbstractAccount account : currentClient.getAccounts()) {
            System.out.println(account.getId());
        }
    }
}
