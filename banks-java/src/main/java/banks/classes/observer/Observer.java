package banks.classes.observer;

import banks.classes.observer.notification.Notification;

public interface Observer {
    void update(Notification notification);
}
