package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.account.AccountTemplate;
import banks.classes.bank.Bank;
import banks.classes.client.Client;
import banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AccountOperations implements IClientOption {
    public void Option(Client currentClient) throws IOException, BankException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.GetInstance(LocalDateTime.now());
        System.out.println("Enter id of your account");
        int accountOperationId = console.nextInt();
        console.nextLine();
        System.out.println("Enter Transaction amount");
        int transactionAmount = console.nextInt();
        console.nextLine();

        AccountTemplate operationAccount = null;
        Bank operationBank = null;
        for (AccountTemplate account : currentClient.GetAccounts())
            if (account.getId() == accountOperationId) {
                operationAccount = account;
                break;
            }
        for (Bank bank : centralBank.GetBanks())
            if (bank.GetAccounts().contains(operationAccount)) {
                operationBank = bank;
                break;
            }

        System.out.println("Select an operation");
        System.out.println("1. Refill");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("Cancel");
        int operation = console.nextInt();
        console.nextLine();
        switch (operation)
        {
            case 1:
                operationBank.Refill(operationAccount, transactionAmount);
                break;
            case 2:
                operationBank.Withdrawal(operationAccount, transactionAmount);
                break;
            case 3:
                System.out.println("Enter id of account you want to transfer to");
                int newAccountId = console.nextInt();
                console.nextLine();
                operationBank.Transfer(operationAccount, FindAccountFromId(newAccountId), transactionAmount);
                break;
            case 4:
        }
    }

    private AccountTemplate FindAccountFromId(int accountId)
    {
        var centralBank = CentralBank.GetInstance(LocalDateTime.now());
        for (Bank bank : centralBank.GetBanks())
            for (AccountTemplate account : bank.GetAccounts())
                if (account.getId() == accountId)
                    return account;
        return null;
    }
}
