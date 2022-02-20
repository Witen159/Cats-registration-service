package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.account.AbstractAccount;
import banks.classes.bank.Bank;
import banks.classes.client.Client;
import banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AccountOperations implements ClientOption {
    public void option(Client currentClient) throws IOException, BankException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter id of your account");
        int accountOperationId = console.nextInt();
        console.nextLine();
        System.out.println("Enter Transaction amount");
        int transactionAmount = console.nextInt();
        console.nextLine();

        AbstractAccount operationAccount = null;
        Bank operationBank = null;
        for (AbstractAccount account : currentClient.getAccounts())
            if (account.getId() == accountOperationId) {
                operationAccount = account;
                break;
            }
        for (Bank bank : centralBank.getBanks())
            if (bank.getAccounts().contains(operationAccount)) {
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
        switch (operation) {
            case 1:
                operationBank.refill(operationAccount, transactionAmount);
                break;
            case 2:
                operationBank.withdrawal(operationAccount, transactionAmount);
                break;
            case 3:
                System.out.println("Enter id of account you want to transfer to");
                int newAccountId = console.nextInt();
                console.nextLine();
                operationBank.transfer(operationAccount, findAccountFromId(newAccountId), transactionAmount);
                break;
            case 4:
        }
    }

    private AbstractAccount findAccountFromId(int accountId) {
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        for (Bank bank : centralBank.getBanks())
            for (AbstractAccount account : bank.getAccounts())
                if (account.getId() == accountId)
                    return account;
        return null;
    }
}
