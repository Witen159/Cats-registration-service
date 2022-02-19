package banks.classes.observer.notification;

public class PercentNotification implements INotification {
    public String Message(String bankName, double amount) {
        return "Debit percent on bank" + bankName + "was changed to" + amount;
    }
}
