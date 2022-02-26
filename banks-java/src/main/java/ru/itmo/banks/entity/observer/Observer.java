package ru.itmo.banks.entity.observer;

import ru.itmo.banks.entity.observer.notification.Notification;

public interface Observer {
    void update(Notification notification);
}
