package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.bank.Bank;
import banks.classes.client.Client;

import java.time.LocalDateTime;

public class BanksList implements IClientOption{
    public void Option(Client currentClient)
    {
        var centralBank = CentralBank.GetInstance(LocalDateTime.now());
        for (Bank bank : centralBank.GetBanks())
        {
            System.out.println(bank.getName() + " id: " + bank.getId());
            if (bank.GetClients().contains(currentClient))
                System.out.println("(You registered)");
            System.out.println();
        }
    }
}
