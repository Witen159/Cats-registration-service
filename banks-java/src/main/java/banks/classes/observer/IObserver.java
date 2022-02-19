package banks.classes.observer;

import banks.classes.observer.notification.INotification;

public interface IObserver {
    void Update(INotification notification);
}
