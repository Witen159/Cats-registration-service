package banks.classes.transaction;

import banks.tools.BankException;

public class CancelTransaction extends AbstractTransaction {
    public CancelTransaction(AbstractTransaction transaction) throws BankException {
        super(transaction.getSender(), transaction.getRecipient(), transaction.getAmountOfMoney(), transaction.getTransactionTime());
        if (transaction instanceof CancelTransaction)
            throw new BankException("You cant cancel CancelTransaction");
        if (getSender() != null) {
            getSender().CancelOperation(getAmountOfMoney());
            getSender().AddTransaction(this);
        }

        if (getRecipient() != null) {
            getRecipient().CancelOperation(-getAmountOfMoney());
            getRecipient().AddTransaction(transaction);
        }
    }
}
