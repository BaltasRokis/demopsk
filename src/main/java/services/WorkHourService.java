package services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Random;

@ApplicationScoped
public class WorkHourService implements Serializable {

    private static final Random random = new Random();

    public static LocalTime[] generate() {
        int startHour = 6 + random.nextInt(9);
        int shiftLength = 4 + random.nextInt(7);
        int endHour = Math.min(startHour + shiftLength, 23);

        return new LocalTime[] {
                LocalTime.of(startHour, 0),
                LocalTime.of(endHour, 0)
        };
    }
}
