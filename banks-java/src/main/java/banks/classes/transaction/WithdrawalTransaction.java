package banks.classes.transaction;

import banks.classes.account.AccountTemplate;
import banks.tools.BankException;

import java.time.LocalDateTime;

public class WithdrawalTransaction extends AbstractTransaction {
    public WithdrawalTransaction(AccountTemplate sender, AccountTemplate recipient, double amountOfMoney, LocalDateTime currentTime) throws BankException {
        super(sender, recipient, amountOfMoney, currentTime);
        getSender().ReduceMoney(amountOfMoney);
        getSender().AddTransaction(this);
    }
}
