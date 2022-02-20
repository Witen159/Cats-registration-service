package banks.classes.observer;

import banks.classes.observer.notification.INotification;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers(INotification notification);
}
