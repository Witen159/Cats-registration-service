package ru.itmo.banks.entity.observer.notification;

public class CommissionNotification implements Notification {
    public String message(String bankName, double amount) {
        return "Commission on on bank" + bankName + "was changed to" + amount;
    }
}
