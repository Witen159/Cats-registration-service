package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.CentralBank;
import banks.classes.account.AbstractAccount;
import banks.classes.client.Client;
import banks.classes.transaction.AbstractTransaction;
import banks.tools.BankException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TransactionCancellation implements ClientOption {
    public void option(Client currentClient) throws IOException, BankException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter id of account and transaction you want to cancel");
        int accountId = console.nextInt();
        int transactionId = console.nextInt();
        console.nextLine();

        for (AbstractAccount account : currentClient.getAccounts()) {
            if (account.getId() == accountId) {
                for (AbstractTransaction transaction : account.getTransactionHistory()) {
                    if (transaction.getId() == transactionId)
                        centralBank.cancelOperation(transaction);
                    return;
                }
            }
        }
    }
}
