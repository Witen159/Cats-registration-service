package banks.consoleApplication.bankManager.bankOptions;

import banks.classes.CentralBank;
import banks.tools.BankException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RewindTime implements IBankOption {
    public void option() throws IOException, BankException {
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
