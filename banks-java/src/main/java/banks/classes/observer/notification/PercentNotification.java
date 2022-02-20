package banks.classes.observer.notification;

public class PercentNotification implements Notification {
    public String message(String bankName, double amount) {
        return "Debit percent on bank" + bankName + "was changed to" + amount;
    }
}
