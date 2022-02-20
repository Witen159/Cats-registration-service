package banks.classes.transaction;

import banks.classes.account.AccountTemplate;
import banks.tools.BankException;

import java.time.LocalDateTime;

public class TransferTransaction extends AbstractTransaction {
    public TransferTransaction(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney, LocalDateTime currentTime) throws BankException {
        super(sender, recipient, amountOfMoney, currentTime);
        getSender().reduceMoney(amountOfMoney);
        getSender().addTransaction(this);

        getRecipient().increaseMoney(amountOfMoney);
        getRecipient().addTransaction(this);
    }
}
