package Common.Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {

    public static String getFutureDateForRailway(int daysToAdd) {

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime futureDate = now.plusDays(daysToAdd);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        return futureDate.format(formatter);
    }

}
