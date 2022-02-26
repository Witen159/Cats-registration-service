package ru.itmo.banks.consoleApplication.bankManager;

import ru.itmo.banks.consoleApplication.bankManager.bankOptions.BankOption;
import ru.itmo.banks.consoleApplication.bankManager.bankOptions.BanksList;
import ru.itmo.banks.consoleApplication.bankManager.bankOptions.RegisterNewBank;
import ru.itmo.banks.consoleApplication.bankManager.bankOptions.RewindTime;

import java.io.IOException;
import java.util.Scanner;

public class CentralBankManager {
    private final Scanner console = new Scanner(System.in);
    private BankOption bankOption = null;

    public void bankManager() throws IOException {
        System.out.println("Menu:");
        System.out.println("1. Register new bank");
        System.out.println("2. Banks list");
        System.out.println("3. Rewind time");
        System.out.println("4. Return to start menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice) {
            case 1:
                bankOption = new RegisterNewBank();
                break;
            case 2:
                bankOption = new BanksList();
                break;
            case 3:
                bankOption = new RewindTime();
                break;
            case 4:
                return;
            default:
                System.out.println("Wrong command");
                bankManager();
                return;
        }

        bankOption.option();
        bankManager();
    }
}
