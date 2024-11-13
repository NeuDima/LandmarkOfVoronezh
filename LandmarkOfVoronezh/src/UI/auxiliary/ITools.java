package UI.auxiliary;

import java.util.ArrayList;
import java.util.Calendar;

public interface ITools {
    int checkNumberInRange(int first, int second);

    int checkNumberLandmarkAndReturnId();

    String calendarToStr(Calendar calendar);

    int checkInt();

    String trimToLength(String str, int max);

    ArrayList<String> breakToLength(String str, int max);
}
