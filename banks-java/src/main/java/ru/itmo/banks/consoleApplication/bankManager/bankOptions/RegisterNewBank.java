package ru.itmo.banks.consoleApplication.bankManager.bankOptions;

import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.bank.PercentAmount;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RegisterNewBank implements BankOption {
    public void option() throws BankException {
        Scanner console = new Scanner(System.in);

        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter the parameters");
        System.out.println("Bank name:");
        String bankName = console.nextLine();

        System.out.println("Operations limit:");
        int operationsLimit = console.nextInt();

        System.out.println("Credit negative limit:");
        int creditLimit = console.nextInt();

        System.out.println("Commission:");
        double commission = console.nextDouble();

        System.out.println("Debit interest on the balance:");
        double debitInterest = console.nextDouble();

        System.out.println("Percent for deposit accounts (count, Money borders (count - 1 times), percents (count times)):");
        int count = console.nextInt();
        var moneyBorders = new ArrayList<Integer>();
        for (int i = 0; i < count - 1; i++) {
            moneyBorders.add(console.nextInt());
        }

        var percents = new ArrayList<Double>();
        for (int i = 0; i < count; i++) {
            percents.add(console.nextDouble());
        }
        console.nextLine();

        var percentAmount = new PercentAmount(moneyBorders, percents);

        centralBank.registerNewBank(bankName, operationsLimit, creditLimit, percentAmount, debitInterest, commission);
    }
}
