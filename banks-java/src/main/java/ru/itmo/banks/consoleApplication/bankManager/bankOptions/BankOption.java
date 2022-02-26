package ru.itmo.banks.consoleApplication.bankManager.bankOptions;

import ru.itmo.banks.tools.BankException;

import java.io.IOException;

public interface BankOption {
    void option() throws BankException, IOException;
}
