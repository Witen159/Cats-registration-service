package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.bank.Bank;
import ru.itmo.banks.entity.client.Client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RegisterInBank implements ClientOption {
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
