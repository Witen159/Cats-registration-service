package banks.consoleApplication.bankManager.bankOptions;

import banks.tools.BankException;

import java.io.IOException;

public interface BankOption {
    void option() throws BankException, IOException;
}
