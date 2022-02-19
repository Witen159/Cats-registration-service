package banks.classes.observer;

import banks.classes.observer.notification.INotification;

public interface IObservable {
    void AddObserver(IObserver observer);

    void RemoveObserver(IObserver observer);

    void NotifyObservers(INotification notification);
}
