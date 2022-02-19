package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreditAccount extends AccountTemplate {
    public CreditAccount(double startMoney, LocalDateTime currentTime, double commission, int creditNegativeLimit, boolean verification) {
        super(startMoney, currentTime, verification);
        setCommission(commission);
        setCreditNegativeLimit(creditNegativeLimit);
    }

    private double Commission; // getter setter

    private int CreditNegativeLimit; // getter setter

    @Override
    public void ReduceMoney(double amountOfMoney) throws BankException {
        IsWillGoOverCreditLimit(amountOfMoney);
        super.ReduceMoney(amountOfMoney);
    }

    @Override
    public void PaymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        long differenceInDays = Duration.between(CurrentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            if (_money < 0) {
                IsWillGoOverCreditLimit(getCommission());
                ReduceMoney(getCommission());
            }

            CurrentTime = getCurrentTime().plusDays(1);
        }
    }

    private void IsWillGoOverCreditLimit(double amountOfMoney) throws BankException {
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
