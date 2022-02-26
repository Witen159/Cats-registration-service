package ru.itmo.banks;

import ru.itmo.banks.consoleApplication.ConsoleApp;
import ru.itmo.banks.tools.BankException;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws BankException, IOException {
        new ConsoleApp();
    }
}
