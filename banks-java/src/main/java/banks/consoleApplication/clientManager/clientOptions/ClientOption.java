package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.client.Client;
import banks.tools.BankException;

import java.io.IOException;

public interface ClientOption {
    void option(Client currentClient) throws IOException, BankException;
}
