package banks.classes.observer.notification;

public class CreditLimitNotification implements INotification {
    public String Message(String bankName, double amount) {
        return "Credit limit on on bank" + bankName + "was changed to" + amount;
    }
}
