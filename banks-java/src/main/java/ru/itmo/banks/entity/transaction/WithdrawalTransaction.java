package ru.itmo.banks.entity.transaction;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;

public class WithdrawalTransaction extends AbstractTransaction {
    public WithdrawalTransaction(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney, LocalDateTime currentTime)  {
        super(sender, recipient, amountOfMoney, currentTime);
        getSender().reduceMoney(amountOfMoney);
        getSender().addTransaction(this);
    }
}
