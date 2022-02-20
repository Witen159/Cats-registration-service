package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class DebitAccount extends AccountTemplate {
    private double _deductions = 0;
    private double InterestOnTheBalance;

    public DebitAccount(double startMoney, LocalDateTime currentTime, double interestOnTheBalance, boolean verification) {
        super(startMoney, currentTime, verification);
        setInterestOnTheBalance(interestOnTheBalance);
    }

    @Override
    public void reduceMoney(double amountOfMoney) throws BankException {
        if (amountOfMoney > _money)
            throw new BankException("Debit account cannot go into negative territory");
        super.reduceMoney(amountOfMoney);
    }

    @Override
    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        var daysControlSystem = new DaysControlSystem();
        long differenceInDays = Duration.between(CurrentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            _deductions += _money * ((getInterestOnTheBalance() * 0.01) / daysControlSystem.daysPerYear(getCurrentTime()));
            if (daysControlSystem.isItLastDayOfMonth(getCurrentTime())) {
                increaseMoney(_deductions);
                _deductions = 0;
            }

            CurrentTime = getCurrentTime().plusDays(1);
        }
    }

    public double getInterestOnTheBalance() {
        return InterestOnTheBalance;
    }

    public void setInterestOnTheBalance(double interestOnTheBalance) {
        InterestOnTheBalance = interestOnTheBalance;
    }
}
