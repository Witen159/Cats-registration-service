package banks.classes.transaction;

import banks.classes.account.AccountTemplate;
import banks.tools.BankException;

import java.time.LocalDateTime;

public class RefillTransaction extends AbstractTransaction {
    public RefillTransaction(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney, LocalDateTime currentTime) throws BankException {
        super(sender, recipient, amountOfMoney, currentTime);
        getRecipient().IncreaseMoney(amountOfMoney);
        getRecipient().AddTransaction(this);
    }
}
