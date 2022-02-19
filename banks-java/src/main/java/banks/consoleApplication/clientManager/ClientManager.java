package banks.consoleApplication.clientManager;

import banks.classes.client.Client;
import banks.classes.client.ClientBuilder;
import banks.classes.client.ClientDirector;
import banks.consoleApplication.clientManager.clientOptions.*;
import banks.tools.BankException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager {
    private ArrayList<Client> _clientsList = new ArrayList<Client>();
    private Client _currentClient = null;
    private ClientDirector _clientDirector = new ClientDirector();
    private ClientBuilder _clientBuilder = new ClientBuilder();
    private IClientOption _clientOption = null;
    private Scanner console = new Scanner(System.in);

    public ClientManager()
    {
        _clientDirector.SetBuilder(_clientBuilder);
    }

    public void LogIn() throws IOException, BankException {
        System.out.println("1. Log in");
        System.out.println("2. Register in system");
        System.out.println("3. Return to start menu");
        int choice = console.nextInt();
        console.nextLine();
        System.out.println();

        switch (choice)
        {
            case 1:
                System.out.println("Type your full name:");
                String fullName = console.nextLine();
                System.out.println();
                _currentClient = null;

                for (Client client : _clientsList)
                {
                    if ((client.getName() + " " + client.getSurname()).equals(fullName))
                    {
                        _currentClient = client;
                        break;
                    }
                }

                if (_currentClient == null)
                {
                    System.out.println("Such a user is not registered");
                    System.out.println();
                    LogIn();
                    return;
                }

                Manager();
                return;
            case 2:
                Registration();

                Manager();
                return;
            case 3:
                return;
            default:
                System.out.println("Wrong command");
                LogIn();
        }
    }

    private void Manager() throws IOException, BankException {
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

        switch (choice)
        {
            case 1:
                _clientOption = new BanksList();
                break;
            case 2:
                _clientOption = new RegisterInBank();
                break;
            case 3:
                _clientOption = new CreateAccount();
                break;
            case 4:
                _clientOption = new AccountsList();
                break;
            case 5:
                _clientOption = new AccountBalance();
                break;
            case 6:
                _clientOption = new AccountOperations();
                break;
            case 7:
                _clientOption = new TransactionHistory();
                break;
            case 8:
                _clientOption = new TransactionCancellation();
                break;
            case 9:
                return;
            default:
                System.out.println("Wrong command");
                Manager();
                return;
        }

        _clientOption.Option(_currentClient);
        Manager();
    }

    private void Registration() throws IOException, BankException {
        System.out.println("To have verified profile you need to add your address and passport");
        System.out.println("Type your name:");
        String name = console.nextLine();
        System.out.println("Type your surname:");
        String surname = console.nextLine();

        for (Client client : _clientsList)
        {
            if (name.equals(client.getName()) && surname.equals(client.getSurname()))
            {
                System.out.println("This user is already registered");
                LogIn();
                return;
            }
        }

        System.out.println("Type your address or tap Enter");
        String address = console.nextLine();

        System.out.println("Type your passport number or tap Enter");
        String passport = console.nextLine();

        if (address.isEmpty() && passport.isEmpty())
            _clientDirector.BuildDefaultClient(name, surname);
        else if (address.isEmpty())
            _clientDirector.BuildClientWithPassport(name, surname, Integer.parseInt(passport));
        else if (passport.isEmpty())
            _clientDirector.BuildClientWithAddress(name, surname, address);
        else
            _clientDirector.BuildFullClient(name, surname, Integer.parseInt(passport), address);

        _currentClient = _clientBuilder.GetClient();
        _clientsList.add(_currentClient);
    }
}
