package banks.classes.observer.notification;

public interface Notification {
    String message(String bankName, double amount);
}
