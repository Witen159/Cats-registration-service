package ru.itmo.banks.entity.transaction;

import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;

public class RefillTransaction extends AbstractTransaction {
    public RefillTransaction(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney, LocalDateTime currentTime)  {
        super(sender, recipient, amountOfMoney, currentTime);
        getRecipient().increaseMoney(amountOfMoney);
        getRecipient().addTransaction(this);
    }
}
