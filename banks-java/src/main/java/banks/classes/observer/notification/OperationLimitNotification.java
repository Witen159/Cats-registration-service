package banks.classes.observer.notification;

public class OperationLimitNotification implements INotification {
    public String message(String bankName, double amount) {
        return "Operation Limit on bank" + bankName + "was changed to" + amount;
    }
}
