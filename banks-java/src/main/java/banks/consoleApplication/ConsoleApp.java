package banks.consoleApplication;

import banks.classes.CentralBank;
import banks.consoleApplication.bankManager.CentralBankManager;
import banks.consoleApplication.clientManager.ClientManager;
import banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleApp {
    private final CentralBank centralBank = CentralBank.getInstance(LocalDateTime.now());
    private final ClientManager clientManager = new ClientManager();
    private final CentralBankManager centralBankManager = new CentralBankManager();
    private final Scanner console = new Scanner(System.in);

    public ConsoleApp() throws BankException, IOException {
        System.out.println("Please use command numbers is our application");
        System.out.println();

        startMenu();
    }

    public void startMenu() throws BankException, IOException {
        System.out.println("Choose who you are:");
        System.out.println("1. Bank manager");
        System.out.println("2. Client");
        System.out.println("3. Exit Menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice) {
            case 1:
                centralBankManager.bankManager();
                break;
            case 2:
                clientManager.logIn();
                break;
            case 3:
                return;
            default:
                System.out.println("Wrong command");
                break;
        }

        startMenu();
    }
}
