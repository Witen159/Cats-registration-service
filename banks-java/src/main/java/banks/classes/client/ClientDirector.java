package banks.classes.client;

import banks.tools.BankException;

public class ClientDirector {
    private static int _clientId = 1;
    private IClientBuilder _builder;

    public void SetBuilder(IClientBuilder builder) {
        _builder = builder;
    }

    public void BuildDefaultClient(String name, String surname) {
        _builder.Reset();
        _builder.BuildName(name);
        _builder.BuildSurname(surname);
        _builder.BuildId(_clientId);
        _clientId++;
        _builder.BuildVerification();
    }

    public void BuildClientWithPassport(String name, String surname, int passportNumber) throws BankException {
        _builder.Reset();
        _builder.BuildName(name);
        _builder.BuildSurname(surname);
        _builder.BuildPassport(passportNumber);
        _builder.BuildId(_clientId);
        _clientId++;
        _builder.BuildVerification();
    }

    public void BuildClientWithAddress(String name, String surname, String address) {
        _builder.Reset();
        _builder.BuildName(name);
        _builder.BuildSurname(surname);
        _builder.BuildAddress(address);
        _builder.BuildId(_clientId);
        _clientId++;
        _builder.BuildVerification();
    }

    public void BuildFullClient(String name, String surname, int passportNumber, String address) throws BankException {
        _builder.Reset();
        _builder.BuildName(name);
        _builder.BuildSurname(surname);
        _builder.BuildPassport(passportNumber);
        _builder.BuildAddress(address);
        _builder.BuildId(_clientId);
        _clientId++;
        _builder.BuildVerification();
    }
}
