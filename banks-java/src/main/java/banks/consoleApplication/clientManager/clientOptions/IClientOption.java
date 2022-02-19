package banks.consoleApplication.clientManager.clientOptions;

import banks.classes.client.Client;
import banks.tools.BankException;

import java.io.IOException;

public interface IClientOption {
    void Option(Client currentClient) throws IOException, BankException;
}
