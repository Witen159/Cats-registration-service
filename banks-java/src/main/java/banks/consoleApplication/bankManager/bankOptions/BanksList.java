package banks.consoleApplication.bankManager.bankOptions;

import banks.classes.CentralBank;
import banks.classes.bank.Bank;

import java.time.LocalDateTime;

public class BanksList implements IBankOption {
    public void option() {
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        for (Bank bank : centralBank.getBanks()) {
            System.out.println(bank.getName() + " id: " + bank.getId());
        }

        System.out.println();
    }
}
