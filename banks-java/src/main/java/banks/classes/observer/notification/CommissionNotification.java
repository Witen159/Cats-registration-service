package banks.classes.observer.notification;

public class CommissionNotification implements INotification {
    public String message(String bankName, double amount) {
        return "Commission on on bank" + bankName + "was changed to" + amount;
    }
}
