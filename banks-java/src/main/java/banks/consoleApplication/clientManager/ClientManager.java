package banks.consoleApplication.clientManager;

import banks.classes.client.Client;
import banks.classes.client.ClientBuilderImpl;
import banks.classes.client.ClientDirector;
import banks.consoleApplication.clientManager.clientOptions.*;
import banks.tools.BankException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager {
    private final ArrayList<Client> clientsList = new ArrayList<Client>();
    private final ClientDirector clientDirector = new ClientDirector();
    private final ClientBuilderImpl clientBuilder = new ClientBuilderImpl();
    private final Scanner console = new Scanner(System.in);
    private ClientOption clientOption = null;
    private Client currentClient = null;

    public ClientManager() {
        clientDirector.setBuilder(clientBuilder);
    }

    public void logIn() throws IOException, BankException {
        System.out.println("1. Log in");
        System.out.println("2. Register in system");
        System.out.println("3. Return to start menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice) {
            case 1:
                System.out.println("Type your full name:");
                String fullName = console.nextLine();
                System.out.println();
                currentClient = null;

                for (Client client : clientsList) {
                    if ((client.getName() + " " + client.getSurname()).equals(fullName)) {
                        currentClient = client;
                        break;
                    }
                }

                if (currentClient == null) {
                    System.out.println("Such a user is not registered");
                    System.out.println();
                    logIn();
                    return;
                }

                manager();
                return;
            case 2:
                registration();

                manager();
                return;
            case 3:
                return;
            default:
                System.out.println("Wrong command");
                logIn();
        }
    }

    private void manager() throws IOException, BankException {
        System.out.println("Menu:");
        System.out.println("1. Banks List");
        System.out.println("2. Register in bank");
        System.out.println("3. Create new account");
        System.out.println("4. Accounts list");
        System.out.println("5. Account balance");
        System.out.println("6. Account operations");
        System.out.println("7. Transaction history");
        System.out.println("8. Request to cancel a transaction");
        System.out.println("9. Return to start menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice) {
            case 1:
                clientOption = new BanksList();
                break;
            case 2:
                clientOption = new RegisterInBank();
                break;
            case 3:
                clientOption = new CreateAccount();
                break;
            case 4:
                clientOption = new AccountsList();
                break;
            case 5:
                clientOption = new AccountBalance();
                break;
            case 6:
                clientOption = new AccountOperations();
                break;
            case 7:
                clientOption = new TransactionHistory();
                break;
            case 8:
                clientOption = new TransactionCancellation();
                break;
            case 9:
                return;
            default:
                System.out.println("Wrong command");
                manager();
                return;
        }

        clientOption.option(currentClient);
        manager();
    }

    private void registration() throws IOException, BankException {
        System.out.println("To have verified profile you need to add your address and passport");
        System.out.println("Type your name:");
        String name = console.nextLine();
        System.out.println("Type your surname:");
        String surname = console.nextLine();

        for (Client client : clientsList) {
            if (name.equals(client.getName()) && surname.equals(client.getSurname())) {
                System.out.println("This user is already registered");
                logIn();
                return;
            }
        }

        System.out.println("Type your address or tap Enter");
        String address = console.nextLine();

        System.out.println("Type your passport number or tap Enter");
        String passport = console.nextLine();

        if (address.isEmpty() && passport.isEmpty())
            clientDirector.buildDefaultClient(name, surname);
        else if (address.isEmpty())
            clientDirector.buildClientWithPassport(name, surname, Integer.parseInt(passport));
        else if (passport.isEmpty())
            clientDirector.buildClientWithAddress(name, surname, address);
        else
            clientDirector.buildFullClient(name, surname, Integer.parseInt(passport), address);

        currentClient = clientBuilder.getClient();
        clientsList.add(currentClient);
    }
}
