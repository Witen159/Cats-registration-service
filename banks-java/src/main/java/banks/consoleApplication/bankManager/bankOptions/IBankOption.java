package banks.consoleApplication.bankManager.bankOptions;

import banks.tools.BankException;

import java.io.IOException;

public interface IBankOption {
    void Option() throws BankException, IOException;
}
