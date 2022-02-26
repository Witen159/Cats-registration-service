package ru.itmo.banks.entity.client;

import ru.itmo.banks.tools.BankException;

public interface ClientBuilder {
    void reset();

    void buildId(int id);

    void buildName(String name);

    void buildSurname(String surname);

    void buildAddress(String address);

    void buildPassport(int passportNumber);

    void buildVerification();

    Client getClient();
}
