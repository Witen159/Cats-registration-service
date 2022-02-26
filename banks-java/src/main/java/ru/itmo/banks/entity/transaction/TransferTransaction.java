package ru.itmo.banks.entity.transaction;

import ru.itmo.banks.entity.account.AbstractAccount;

import java.time.LocalDateTime;

public class TransferTransaction extends AbstractTransaction {
    public TransferTransaction(AbstractAccount sender, AbstractAccount recipient, double amountOfMoney, LocalDateTime currentTime) {
        super(sender, recipient, amountOfMoney, currentTime);
        getSender().reduceMoney(amountOfMoney);
        getSender().addTransaction(this);

        getRecipient().increaseMoney(amountOfMoney);
        getRecipient().addTransaction(this);
    }
}
