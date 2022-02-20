package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.bank.Bank;
import banks.classes.client.Client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RegisterInBank implements IClientOption {
    public void option(Client currentClient) throws IOException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter id of bank you want to register");
        int bankId = console.nextInt();
        console.nextLine();
        for (Bank bank : centralBank.getBanks()) {
            if (bank.getId() == bankId)
                bank.registerNewClient(currentClient);
            break;
        }
    }
}
