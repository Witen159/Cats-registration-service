package ru.itmo.banks.consoleApplication.bankManager.bankOptions;

import ru.itmo.banks.entity.CentralBank;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RewindTime implements BankOption {
    public void option() throws IOException {
        Scanner console = new Scanner(System.in);
        var centralBank = CentralBank.getInstance(LocalDateTime.now());
        System.out.println("Enter the date you want to wait until (year, month, day)");
        var newDate = LocalDateTime.of(
                console.nextInt(),
                console.nextInt(),
                console.nextInt(),
                1, 1);
        console.nextLine();

        if (Duration.between(centralBank.getCurrentTime(), newDate).toDays() < 1)
            System.out.println("New date should be at least next day");
        else
            centralBank.newDate(newDate);
    }
}
