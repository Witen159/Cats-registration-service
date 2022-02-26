package ru.itmo.banks.entity.observer.notification;

public class OperationLimitNotification implements Notification {
    public String message(String bankName, double amount) {
        return "Operation Limit on bank" + bankName + "was changed to" + amount;
    }
}
