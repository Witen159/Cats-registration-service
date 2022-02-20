package banks.classes.client;

import banks.tools.BankException;

public class ClientDirector {
    private static int clientId = 1;
    private IClientBuilder builder;

    public void setBuilder(IClientBuilder builder) {
        this.builder = builder;
    }

    public void buildDefaultClient(String name, String surname) {
        builder.reset();
        builder.buildName(name);
        builder.buildSurname(surname);
        builder.buildId(clientId);
        clientId++;
        builder.buildVerification();
    }

    public void buildClientWithPassport(String name, String surname, int passportNumber) throws BankException {
        builder.reset();
        builder.buildName(name);
        builder.buildSurname(surname);
        builder.buildPassport(passportNumber);
        builder.buildId(clientId);
        clientId++;
        builder.buildVerification();
    }

    public void buildClientWithAddress(String name, String surname, String address) {
        builder.reset();
        builder.buildName(name);
        builder.buildSurname(surname);
        builder.buildAddress(address);
        builder.buildId(clientId);
        clientId++;
        builder.buildVerification();
    }

    public void buildFullClient(String name, String surname, int passportNumber, String address) throws BankException {
        builder.reset();
        builder.buildName(name);
        builder.buildSurname(surname);
        builder.buildPassport(passportNumber);
        builder.buildAddress(address);
        builder.buildId(clientId);
        clientId++;
        builder.buildVerification();
    }
}
