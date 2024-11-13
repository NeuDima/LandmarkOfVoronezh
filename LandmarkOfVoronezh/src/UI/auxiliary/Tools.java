package UI.auxiliary;

import service.Landmark.LandmarkService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Tools implements ITools {
    Scanner scanner = new Scanner(System.in);

    @Override
    public int checkNumberInRange(int first, int second) {
        while(true) {
            int value = checkInt();
            if (value >= first && value <= second) {
                return value;
            }
            System.out.print("Введите целое число от " + first + " до " + second + ": ");
        }
    }

    @Override
    public int checkNumberLandmarkAndReturnId() {
        while(true) {
            int value = checkInt();
            if (value <= new LandmarkService().getLengthLandmarkTable() && value > 0) {
                return value;
            } else {
                System.out.println("Нет достопримечательности с таким номером");
            }
        }
    }

    @Override
    public String calendarToStr(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int checkInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.nextLine();
            }
            System.out.print("Пожалуйста, введите номер нужного пункта: ");
        }
    }

    @Override
    public String trimToLength(String str, int max) {
        return str.length() > max ? (str.substring(0, max) + "...") : str;
    }

    @Override
    public ArrayList<String> breakToLength(String str, int max) {
        ArrayList<String> list = new ArrayList<>();
        char[] strChar = str.toCharArray();

        int lastIndex = 0;
        for (int i = max; i < strChar.length; i += max) {
            for (int j = 0; j < i + max; j++) {
                if (strChar[i + j] == ' ') {
                    list.add(str.substring(lastIndex, i + j));
                    lastIndex = i + j + 1;
                    break;
                }
            }
        }
        list.add(str.substring(lastIndex, strChar.length));
        return list;
    }
}
