package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.bank.Bank;
import banks.classes.client.Client;

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
