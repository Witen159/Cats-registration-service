package ru.itmo.banks.entity.observer.notification;

public class CreditLimitNotification implements Notification {
    public String message(String bankName, double amount) {
        return "Credit limit on on bank" + bankName + "was changed to" + amount;
    }
}
