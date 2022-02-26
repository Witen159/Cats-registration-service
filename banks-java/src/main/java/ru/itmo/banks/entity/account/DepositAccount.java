package ru.itmo.banks.entity.account;

import ru.itmo.banks.tools.BankException;

import java.time.Duration;
import java.time.LocalDateTime;

public class DepositAccount extends AbstractAccount {
    private double deductions = 0;
    private double interestOnTheBalance;
    private LocalDateTime depositCloseTime;
    private boolean accountIsOpen;

    public DepositAccount(double startMoney, LocalDateTime currentTime, double interestOnTheBalance, LocalDateTime depositCloseTime, boolean verification) throws BankException {
        super(startMoney, currentTime, verification);
        if (Duration.between(currentTime, depositCloseTime).toDays() < 30)
            throw new BankException("Deposit account must be opened for at least 30 days");
        setDepositCloseTime(depositCloseTime);
        setInterestOnTheBalance(interestOnTheBalance);
        accountIsOpen = true;
    }

    @Override
    public void reduceMoney(double amountOfMoney) throws BankException {
        if (isAccountOpen())
            throw new BankException("You cannot withdraw money from the account until it is close");
        if (amountOfMoney > money)
            throw new BankException("Deposit account cannot go into negative territory");
        super.reduceMoney(amountOfMoney);
    }

    @Override
    public void paymentOperation(LocalDateTime timeOfTheNewPayment) throws BankException {
        var daysControlSystem = new DaysControlSystem();
        long differenceInDays = Duration.between(currentTime, timeOfTheNewPayment).toDays();
        for (int days = 0; days < differenceInDays; days++) {
            if (isAccountOpen()) {
                deductions += money * ((getInterestOnTheBalance() * 0.01) / daysControlSystem.daysPerYear(getCurrentTime()));

                if (daysControlSystem.isItLastDayOfMonth(getCurrentTime())) {
                    increaseMoney(deductions);
                    deductions = 0;
                }

                if (currentTime.isEqual(depositCloseTime)) {
                    increaseMoney(deductions);
                    deductions = 0;
                    accountIsOpen = false;
                }
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

    public LocalDateTime getDepositCloseTime() {
        return depositCloseTime;
    }

    public void setDepositCloseTime(LocalDateTime depositCloseTime) {
        this.depositCloseTime = depositCloseTime;
    }

    public boolean isAccountOpen() {
        return accountIsOpen;
    }
}
