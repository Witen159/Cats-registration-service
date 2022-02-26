package ru.itmo.banks.consoleApplication.clientManager.clientOptions;

import ru.itmo.banks.entity.client.Client;

import java.io.IOException;

public interface ClientOption {
    void option(Client currentClient) throws IOException;
}
