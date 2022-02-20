package banks.classes.transaction;

import banks.classes.account.AbstractAccount;
import banks.tools.BankException;

import java.time.LocalDateTime;

public class WithdrawalTransaction extends AbstractTransaction {
    public WithdrawalTransaction(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney, LocalDateTime currentTime) throws BankException {
        super(sender, recipient, amountOfMoney, currentTime);
        getSender().reduceMoney(amountOfMoney);
        getSender().addTransaction(this);
    }
}
