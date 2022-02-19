package banks.consoleApplication;

import banks.classes.CentralBank;
import banks.consoleApplication.bankManager.CentralBankManager;
import banks.consoleApplication.clientManager.ClientManager;
import banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleApp {
    private CentralBank _centralBank = CentralBank.GetInstance(LocalDateTime.now());
    private ClientManager _clientManager = new ClientManager();
    private CentralBankManager _centralBankManager = new CentralBankManager();
    private Scanner console = new Scanner(System.in);
    public ConsoleApp() throws BankException, IOException {
        System.out.println("Please use command numbers is our application");
        System.out.println();

        StartMenu();
    }

    public void StartMenu() throws BankException, IOException {
        System.out.println("Choose who you are:");
        System.out.println("1. Bank manager");
        System.out.println("2. Client");
        System.out.println("3. Exit Menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice)
        {
            case 1:
                _centralBankManager.BankManager();
                break;
            case 2:
                _clientManager.LogIn();
                break;
            case 3:
                return;
            default:
                System.out.println("Wrong command");
                break;
        }

        StartMenu();
    }
}
