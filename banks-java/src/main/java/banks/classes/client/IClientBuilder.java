package banks.classes.client;

import banks.tools.BankException;

public interface IClientBuilder {
    void Reset();

    void BuildId(int id);

    void BuildName(String name);

    void BuildSurname(String surname);

    void BuildAddress(String address);

    void BuildPassport(int passportNumber) throws BankException;

    void BuildVerification();
}
