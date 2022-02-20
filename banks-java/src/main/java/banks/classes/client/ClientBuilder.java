package banks.classes.client;

import banks.tools.BankException;

public interface ClientBuilder {
    void reset();

    void buildId(int id);

    void buildName(String name);

    void buildSurname(String surname);

    void buildAddress(String address);

    void buildPassport(int passportNumber) throws BankException;

    void buildVerification();

    Client getClient();
}
