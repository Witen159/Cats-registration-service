package ru.itmo.banks.entity.transaction;

import ru.itmo.banks.tools.BankException;

public class CancelTransaction extends AbstractTransaction {
    public CancelTransaction(AbstractTransaction transaction) throws BankException {
        super(transaction.getSender(), transaction.getRecipient(), transaction.getAmountOfMoney(), transaction.getTransactionTime());

        if (transaction instanceof CancelTransaction)
            throw new BankException("You cant cancel CancelTransaction");

        if (getSender() != null) {
            getSender().cancelOperation(getAmountOfMoney());
            getSender().addTransaction(this);
        }

        if (getRecipient() != null) {
            getRecipient().cancelOperation(-getAmountOfMoney());
            getRecipient().addTransaction(transaction);
        }
    }
}
