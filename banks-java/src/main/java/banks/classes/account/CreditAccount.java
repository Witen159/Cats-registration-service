package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreditAccount extends AccountTemplate {
    private double Commission;
    private int CreditNegativeLimit;

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
        long differenceInDays = Duration.between(CurrentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            if (_money < 0) {
                isWillGoOverCreditLimit(getCommission());
                reduceMoney(getCommission());
            }

            CurrentTime = getCurrentTime().plusDays(1);
        }
    }

    private void isWillGoOverCreditLimit(double amountOfMoney) throws BankException {
        if (_money - amountOfMoney < -getCreditNegativeLimit())
            throw new BankException("The balance fell below the credit limit");
    }

    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public int getCreditNegativeLimit() {
        return CreditNegativeLimit;
    }

    public void setCreditNegativeLimit(int creditNegativeLimit) {
        CreditNegativeLimit = creditNegativeLimit;
    }
}
