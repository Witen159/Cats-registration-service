package banks.tools;

public class BankException extends Exception {
    public BankException()
    {
    }

    public BankException(String message)
    {
        super(message);
    }

    public BankException(String message, Exception innerException)
    {
        super(message, innerException);
    }
}
