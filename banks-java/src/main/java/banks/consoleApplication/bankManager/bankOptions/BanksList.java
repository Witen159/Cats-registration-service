package banks.consoleApplication.bankManager.bankOptions;

import banks.classes.CentralBank;
import banks.classes.bank.Bank;

import java.time.LocalDateTime;

public class BanksList implements IBankOption{
    public void Option()
    {
        var centralBank = CentralBank.GetInstance(LocalDateTime.now());
        for (Bank bank : centralBank.GetBanks())
        {
            System.out.println(bank.getName() + " id: " + bank.getId());
        }

        System.out.println();
    }
}
