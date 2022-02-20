package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreditAccount extends AbstractAccount {
    private double commission;
    private int creditNegativeLimit;

    public CreditAccount(double startMoney, LocalDateTime currentTime, double commission, int creditNegativeLimit, boolean verification) {
        super(startMoney, currentTime, verification);
        setCommission(commission);
        setCreditNegativeLimit(creditNegativeLimit);
    }

    @Override
    public void reduceMoney(double amountOfMoney) throws BankException {
        isWillGoOverCreditLimit(amountOfMoney);
        super.reduceMoney(amountOfMoney);
    }

    @Override
    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        long differenceInDays = Duration.between(currentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            if (money < 0) {
                isWillGoOverCreditLimit(getCommission());
                reduceMoney(getCommission());
            }

            currentTime = getCurrentTime().plusDays(1);
        }
    }

    private void isWillGoOverCreditLimit(double amountOfMoney) throws BankException {
        if (money - amountOfMoney < -getCreditNegativeLimit())
            throw new BankException("The balance fell below the credit limit");
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getCreditNegativeLimit() {
        return creditNegativeLimit;
    }

    public void setCreditNegativeLimit(int creditNegativeLimit) {
        this.creditNegativeLimit = creditNegativeLimit;
    }
}
