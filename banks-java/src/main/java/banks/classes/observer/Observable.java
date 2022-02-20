package banks.classes.observer;

import banks.classes.observer.notification.Notification;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Notification notification);
}
