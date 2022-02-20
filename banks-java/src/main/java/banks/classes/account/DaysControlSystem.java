package banks.classes.account;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DaysControlSystem {
    public boolean isItLastDayOfMonth(LocalDateTime currentTime) {
        var lastDayOfMonth = LocalDate.of(currentTime.getYear(), currentTime.getMonth(), 1).plusMonths(1).minusDays(1);
        var thisDay = LocalDate.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth());
        return thisDay.equals(lastDayOfMonth);
    }

    public long daysPerYear(LocalDateTime currentTime) {
        LocalDateTime thisYear = LocalDateTime.of(currentTime.getYear(), 1, 1, 1, 1);
        LocalDateTime nextYear = LocalDateTime.of(currentTime.getYear() + 1, 1, 1, 1, 1);
        return Duration.between(thisYear, nextYear).toDays();
    }
}
