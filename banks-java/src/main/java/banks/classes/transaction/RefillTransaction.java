package banks.classes.transaction;

import banks.classes.account.AbstractAccount;
import banks.tools.BankException;

import java.time.LocalDateTime;

public class RefillTransaction extends AbstractTransaction {
    public RefillTransaction(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney, LocalDateTime currentTime) throws BankException {
        super(sender, recipient, amountOfMoney, currentTime);
        getRecipient().increaseMoney(amountOfMoney);
        getRecipient().addTransaction(this);
    }
}
