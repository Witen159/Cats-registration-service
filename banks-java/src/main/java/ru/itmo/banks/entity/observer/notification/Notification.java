package ru.itmo.banks.entity.observer.notification;

public interface Notification {
    String message(String bankName, double amount);
}
