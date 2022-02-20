package banks.classes.observer;

import banks.classes.observer.notification.INotification;

public interface IObserver {
    void update(INotification notification);
}
