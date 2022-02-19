package banks.classes.account;

import banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class DepositAccount extends AccountTemplate {
    private double _deductions = 0;

    public DepositAccount(double startMoney, LocalDateTime currentTime, double interestOnTheBalance, LocalDateTime depositCloseTime, boolean verification) throws BankException {
        super(startMoney, currentTime, verification);
        if (Duration.between(currentTime, depositCloseTime).toDays() < 30)
            throw new BankException("Deposit account must be opened for at least 30 days");
        setDepositCloseTime(depositCloseTime);
        setInterestOnTheBalance(interestOnTheBalance);
        AccountIsOpen = true;
    }

    private double InterestOnTheBalance; //getter setter

    private LocalDateTime DepositCloseTime; // getter setter

    private boolean AccountIsOpen; //getter

    @Override
    public void ReduceMoney(double amountOfMoney) throws BankException {
        if (isAccountOpen())
            throw new BankException("You cannot withdraw money from the account until it is close");
        if (amountOfMoney > _money)
            throw new BankException("Deposit account cannot go into negative territory");
        super.ReduceMoney(amountOfMoney);
    }

    @Override
    public void PaymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        var daysControlSystem = new DaysControlSystem();
        long differenceInDays = Duration.between(CurrentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            if (isAccountOpen()) {
                _deductions += _money * ((getInterestOnTheBalance() * 0.01) / daysControlSystem.DaysPerYear(getCurrentTime()));

                if (daysControlSystem.IsItLastDayOfMonth(getCurrentTime())) {
                    IncreaseMoney(_deductions);
                    _deductions = 0;
                }

                if (CurrentTime.isEqual(DepositCloseTime)) {
                    IncreaseMoney(_deductions);
                    _deductions = 0;
                    AccountIsOpen = false;
                }
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

    public LocalDateTime getDepositCloseTime() {
        return DepositCloseTime;
    }

    public void setDepositCloseTime(LocalDateTime depositCloseTime) {
        DepositCloseTime = depositCloseTime;
    }

    public boolean isAccountOpen() {
        return AccountIsOpen;
    }
}
