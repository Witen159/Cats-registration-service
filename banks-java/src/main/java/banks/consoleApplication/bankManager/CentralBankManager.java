package banks.consoleApplication.bankManager;

import banks.consoleApplication.bankManager.bankOptions.BanksList;
import banks.consoleApplication.bankManager.bankOptions.IBankOption;
import banks.consoleApplication.bankManager.bankOptions.RegisterNewBank;
import banks.consoleApplication.bankManager.bankOptions.RewindTime;
import banks.tools.BankException;

import java.io.IOException;
import java.util.Scanner;

public class CentralBankManager {
    private IBankOption _bankOption = null;
    private Scanner console = new Scanner(System.in);
    public void BankManager() throws BankException, IOException {
        System.out.println("Menu:");
        System.out.println("1. Register new bank");
        System.out.println("2. Banks list");
        System.out.println("3. Rewind time");
        System.out.println("4. Return to start menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice)
        {
            case 1:
                _bankOption = new RegisterNewBank();
                break;
            case 2:
                _bankOption = new BanksList();
                break;
            case 3:
                _bankOption = new RewindTime();
                break;
            case 4:
                return;
            default:
                System.out.println("Wrong command");
                BankManager();
                return;
        }

        _bankOption.Option();
        BankManager();
    }
}
