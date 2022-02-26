package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.bank.Bank;
import ru.itmo.banks.entity.client.Client;
import ru.itmo.banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateAccount implements ClientOption {
    public void option(Client currentClient) throws IOException, BankException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter id of bank you want to create account");
        int bankId = console.nextInt();
        console.nextLine();
        System.out.println();

        Bank bankToAdd = null;
        for (Bank bank : centralBank.getBanks()) {
            if (bank.getId() == bankId)
                bankToAdd = bank;
            break;
        }

        if (bankToAdd == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Enter what type of account you want to create");
        System.out.println("1. Debit");
        System.out.println("2. Deposit");
        System.out.println("3. Credit");
        System.out.println("4. Cancel");
        int accountType = console.nextInt();
        console.nextLine();

        System.out.println("Enter start amount of money");
        int startAmountOfMoney = console.nextInt();
        console.nextLine();

        switch (accountType) {
            case 1:
                bankToAdd.addDebitAccount(currentClient, startAmountOfMoney);
                break;
            case 2:
                System.out.println("Enter the account closing date (year, month, day)");
                var closeDate = LocalDateTime.of(
                        console.nextInt(),
                        console.nextInt(),
                        console.nextInt(),
                        1, 1);
                console.nextLine();
                bankToAdd.addDepositAccount(currentClient, startAmountOfMoney, closeDate);
                break;
            case 3:
                bankToAdd.addCreditAccount(currentClient, startAmountOfMoney);
                break;
            case 4:
        }
    }
}
