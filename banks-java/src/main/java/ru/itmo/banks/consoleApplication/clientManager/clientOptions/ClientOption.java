package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.client.Client;
import ru.itmo.banks.tools.BankException;

import java.io.IOException;

public interface ClientOption {
    void option(Client currentClient) throws IOException, BankException;
}
