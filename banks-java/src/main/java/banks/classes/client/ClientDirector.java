package banks.classes.client;

import banks.tools.BankException;

public class ClientDirector {
    private static int _clientId = 1;
    private IClientBuilder _builder;

    public void setBuilder(IClientBuilder builder) {
        _builder = builder;
    }

    public void buildDefaultClient(String name, String surname) {
        _builder.reset();
        _builder.buildName(name);
        _builder.buildSurname(surname);
        _builder.buildId(_clientId);
        _clientId++;
        _builder.buildVerification();
    }

    public void buildClientWithPassport(String name, String surname, int passportNumber) throws BankException {
        _builder.reset();
        _builder.buildName(name);
        _builder.buildSurname(surname);
        _builder.buildPassport(passportNumber);
        _builder.buildId(_clientId);
        _clientId++;
        _builder.buildVerification();
    }

    public void buildClientWithAddress(String name, String surname, String address) {
        _builder.reset();
        _builder.buildName(name);
        _builder.buildSurname(surname);
        _builder.buildAddress(address);
        _builder.buildId(_clientId);
        _clientId++;
        _builder.buildVerification();
    }

    public void buildFullClient(String name, String surname, int passportNumber, String address) throws BankException {
        _builder.reset();
        _builder.buildName(name);
        _builder.buildSurname(surname);
        _builder.buildPassport(passportNumber);
        _builder.buildAddress(address);
        _builder.buildId(_clientId);
        _clientId++;
        _builder.buildVerification();
    }
}
