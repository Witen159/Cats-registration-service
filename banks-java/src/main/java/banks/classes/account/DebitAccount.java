package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class DebitAccount extends AbstractAccount {
    private double deductions = 0;
    private double interestOnTheBalance;

    public DebitAccount(double startMoney, LocalDateTime currentTime, double interestOnTheBalance, boolean verification) {
        super(startMoney, currentTime, verification);
        setInterestOnTheBalance(interestOnTheBalance);
    }

    @Override
    public void reduceMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney > money)
            throw new BankException("Debit account cannot go into negative territory");
        super.reduceMoney(amountOfMoney);
    }

    @Override
    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        var daysControlSystem = new DaysControlSystem();
        long differenceInDays = Duration.between(currentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            deductions += money * ((getInterestOnTheBalance() * 0.01) / daysControlSystem.daysPerYear(getCurrentTime()));
            if (daysControlSystem.isItLastDayOfMonth(getCurrentTime())) {
                increaseMoney(deductions);
                deductions = 0;
            }

            currentTime = getCurrentTime().plusDays(1);
        }
    }

    public double getInterestOnTheBalance() {
        return interestOnTheBalance;
    }

    public void setInterestOnTheBalance(double interestOnTheBalance) {
        this.interestOnTheBalance = interestOnTheBalance;
    }
}
