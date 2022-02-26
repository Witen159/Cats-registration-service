package ru.itmo.banks.entity.observer;

import ru.itmo.banks.entity.observer.notification.Notification;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Notification notification);
}
